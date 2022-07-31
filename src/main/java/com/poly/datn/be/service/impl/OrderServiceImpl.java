package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.*;
import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.domain.dto.ReqUpdateOrderDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.domain.model.AmountMonth;
import com.poly.datn.be.domain.model.AmountYear;
import com.poly.datn.be.domain.model.CountOrder;
import com.poly.datn.be.domain.model.ReportProduct;
import com.poly.datn.be.entity.*;
import com.poly.datn.be.repo.OrderRepo;
import com.poly.datn.be.service.*;
import com.poly.datn.be.util.ConvertUtil;
import com.poly.datn.be.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AttributeService attributeService;
    @Autowired
    VoucherService voucherService;

    @Override
    @Transactional
    public Order createOrder(ReqOrderDto reqOrderDto) {
        for(OrderDetail o: reqOrderDto.getOrderDetails()){
            Attribute attribute = attributeService.findById(o.getAttribute().getId());
            if(attribute.getStock() < o.getQuantity()){
                throw new AppException(AttributeConst.ATTRIBUTE_MSG_ERROR_NOT_ENOUGH_STOCK);
            }else{
                attribute.setStock(attribute.getStock() - o.getQuantity());
                attribute.setCache(attribute.getCache() + o.getQuantity());
                attributeService.save(attribute);
            }
        }
        Account account = accountService.findById(reqOrderDto.getAccountId());
        OrderStatus orderStatus = orderStatusService.getById(1L);
        Order order = ConvertUtil.fromReqOrderDto(reqOrderDto);
        order.setAccount(account);
        order.setOrderStatus(orderStatus);
        if(!reqOrderDto.getCode().isEmpty()){
            Voucher voucher = voucherService.getVoucherByCode(reqOrderDto.getCode());
            voucher.setCount(voucher.getCount() - 1);
            voucherService.saveVoucher(voucher);
            order.setVoucher(voucher);
        }
        order = orderRepo.save(order);
        for(OrderDetail o: reqOrderDto.getOrderDetails()){
            o.setOrder(order);
            orderDetailService.createOrderDetail(o);

            CartItem c = cartItemService.findCartItemByAccountIdAndAttributeId(account.getId(), o.getAttribute().getId());
            c.setQuantity(CartItemConst.CART_ITEM_QUANTITY_WAITING);
            c.setIsActive(CartItemConst.CART_ITEM_INACTIVE);
            cartItemService.saveCartItem(c);
        }
       try {
           if(order.getTotal() > OrderConst.ORDER_TOTAL_BASE){
               MailUtil.sendmail(order.getEmail(), order.getId());
           }
       }catch (MessagingException e){
           throw new AppException(e.getMessage());
       }
        return order;
    }

    @Override
    public Page<Order> getOrderByAccount(Long id, Pageable pageable) {
        Account account = accountService.findById(id);
        return orderRepo.findAllByAccount_Id(id, pageable);
    }

    @Override
    public Order getByOrderId(Long id) {
        Optional<Order> optional = orderRepo.findById(id);
        if(!optional.isPresent()){
            throw new AppException(OrderConst.ORDER_MSG_ERROR_NOT_EXIST);
        }
        return optional.get();
    }

    @Override
    public List<OrderDetail> getAllByOrderId(Long id) {
        return orderDetailService.getAllByOrderId(id);
    }

    @Override
    public Page<Order> findOrderByAccountIdAndOrderStatusId(Long accountId, Long orderStatusId, Pageable pageable) {
        OrderStatus orderStatus = orderStatusService.getById(orderStatusId);
        if(orderStatus == null){
            return orderRepo.findAllByAccount_Id(accountId, pageable);
        }
        return orderRepo.findOrderByAccount_IdAndOrderStatus_Id(accountId, orderStatusId, pageable);
    }

    @Override
    public Page<Order> getAllOrdersAndPagination(Long id, Pageable pageable) {
        OrderStatus orderStatus = orderStatusService.getById(id);
        if(orderStatus == null){
            return orderRepo.findAll(pageable);
        }
        return orderRepo.findOrderByOrderStatus_Id(id, pageable);
    }

    @Override
    @Transactional
    public Order updateOrderWithStatus(Long orderId, Long statusId) {
        Order order = getByOrderId(orderId);
        OrderStatus orderStatus = orderStatusService.getById(statusId);
        if(orderStatus == null){
            throw new AppException(OrderStatusConst.ORDER_STATUS_MSG_ERROR_NOT_EXIST);
        }
        if(order.getOrderStatus().getId().equals(OrderStatusConst.ORDER_STATUS_CANCEL) || order.getOrderStatus().getId().equals(OrderStatusConst.ORDER_STATUS_SUCCESS)){
            throw new AppException(OrderStatusConst.ORDER_STATUS_MSG_ERROR_NOT_EXIST);
        }
        if(order.getOrderStatus().getId().equals(orderStatus.getId())){
            throw new AppException(OrderConst.ORDER_MSG_ERROR_ALREADY_STATUS);
        }
        if(order.getOrderStatus().getId() > statusId){
            throw new AppException(OrderStatusConst.ORDER_STATUS_MSG_ERROR_NOT_EXIST);
        }
        if(statusId == OrderStatusConst.ORDER_STATUS_CANCEL){
            attributeService.backAttribute(orderId);
            Voucher voucher = order.getVoucher();
            if(voucher != null){
                voucher.setCount(voucher.getCount() + 1);
                voucherService.saveVoucher(voucher);
            }
        }
        if(statusId == OrderStatusConst.ORDER_STATUS_SUCCESS){
            order.setIsPending(true);
            attributeService.cacheAttribute(orderId);
        }
        order.setOrderStatus(orderStatus);
        order.setModifyDate(LocalDate.now());
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(ReqUpdateOrderDto reqUpdateOrderDto) {
        Optional<Order> optionalOrder = orderRepo.findById(reqUpdateOrderDto.getOrderId());
        if(!optionalOrder.isPresent()){
            throw new AppException(OrderConst.ORDER_MSG_ERROR_NOT_EXIST);
        }
        Order order = optionalOrder.get();
        order.setIsPending(reqUpdateOrderDto.getIsPending());
        order.setAddress(reqUpdateOrderDto.getAddress());
        order.setEmail(reqUpdateOrderDto.getEmail());
        order.setFullname(reqUpdateOrderDto.getFullname());
        order.setNote(reqUpdateOrderDto.getNote());
        order.setPhone(reqUpdateOrderDto.getPhone());
        order.setModifyDate(LocalDate.now());
        return orderRepo.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) {
        Order order = getByOrderId(orderId);
        if(order.getOrderStatus().getId() == OrderStatusConst.ORDER_STATUS_SHIPPING){
            throw new AppException("Đơn hàng đang được vận chuyển.");
        }
        if(order.getOrderStatus().getId() == OrderStatusConst.ORDER_STATUS_CANCEL){
            throw new AppException("Đơn hàng đã được hủy.");
        }
        if(order.getOrderStatus().getId() == OrderStatusConst.ORDER_STATUS_SUCCESS){
            throw new AppException("Đơn hàng đã được giao thành công.");
        }
        OrderStatus orderStatus = orderStatusService.getById(OrderStatusConst.ORDER_STATUS_CANCEL);
        order.setOrderStatus(orderStatus);
        return orderRepo.save(order);
    }

    @Override
    public Page<Order> findOrderByAccount_Id(Long id, Pageable pageable) {
        return orderRepo.findOrderByAccount_Id(id, pageable);
    }

    @Override
    public Page<Order> findOrderByOrderStatusAndYearAndMonth(Long id, Integer year, Integer month, Pageable pageable) {
        if(id.equals(0L)){
           return orderRepo.findOrderByYearAndMonth(year, month, pageable);
        }
        return orderRepo.findOrderByOrderStatusAndYearAndMonth(id, year, month, pageable);
    }

    @Override
    public Page<Order> findOrderBetweenDate(Long id, LocalDate from, LocalDate to, Pageable pageable) {
        if(id.equals(0L)){
            return orderRepo.findOrderBetweenDate(from, to, pageable);
        }
        return orderRepo.findOrderByOrderStatusBetweenDate(id, from, to, pageable);
    }

    @Override
    public Page<ReportProduct> reportByProduct(Pageable pageable) {
        return orderRepo.reportByProduct(pageable);
    }

    @Override
    public Page<Order> findOrderByProduct(Long id, Pageable pageable) {
        return orderRepo.findOrderByProduct(id, pageable);
    }

    @Override
    public List<AmountYear> reportAmountYear() {
        return orderRepo.reportAmountYear();
    }

    @Override
    public List<AmountMonth> reportAmountMonth(Integer year) {
        return orderRepo.reportAmountMonth(year);
    }

    @Override
    public Integer countOrder() {
        return orderRepo.findAll().size();
    }

    @Override
    public List<CountOrder> countOrderByName() {
        return orderRepo.countOrderByName();
    }

}
