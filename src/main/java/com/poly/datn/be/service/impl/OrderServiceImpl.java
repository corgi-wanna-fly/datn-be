package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.AttributeConst;
import com.poly.datn.be.domain.constant.CartItemConst;
import com.poly.datn.be.domain.constant.OrderConst;
import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.*;
import com.poly.datn.be.repo.OrderRepo;
import com.poly.datn.be.service.*;
import com.poly.datn.be.util.ConvertUtil;
import com.poly.datn.be.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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
    public List<Order> getOrderByAccount(Long id) {
        Account account = accountService.findById(id);
        return orderRepo.findAllByAccount_Id(id);
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
    public List<Order> findOrderByAccountIdAndOrderStatusId(Long accountId, Long orderStatusId) {
        OrderStatus orderStatus = orderStatusService.getById(orderStatusId);
        if(orderStatus == null){
            return orderRepo.findAllByAccount_Id(accountId);
        }
        return orderRepo.findOrderByAccount_IdAndOrderStatus_Id(accountId, orderStatusId);
    }
}
