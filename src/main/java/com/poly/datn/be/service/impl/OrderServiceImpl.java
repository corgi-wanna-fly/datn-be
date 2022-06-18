package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.OrderDetail;
import com.poly.datn.be.entity.OrderStatus;
import com.poly.datn.be.repo.OrderRepo;
import com.poly.datn.be.service.*;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

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

    @Override
    @Transactional
    public Order createOrder(ReqOrderDto reqOrderDto){
        Account account = accountService.findById(reqOrderDto.getAccountId());
        OrderStatus orderStatus = orderStatusService.getById(1L);
        Order order = ConvertUtil.fromReqOrderDto(reqOrderDto);
        order.setAccount(account);
        order.setOrderStatus(orderStatus);
        order = orderRepo.save(order);
        for(OrderDetail o: reqOrderDto.getOrderDetails()){
            o.setOrder(order);
            orderDetailService.createOrderDetail(o);
        }
        cartItemService.clearCartItem(reqOrderDto.getAccountId());
        return order;
    }

    @Override
    public List<Order> getOrderByAccount(Long id) {
        Account account = accountService.findById(id);
        return orderRepo.findAllByAccount_Id(id);
    }
}
