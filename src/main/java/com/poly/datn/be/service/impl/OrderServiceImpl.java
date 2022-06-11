package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.OrderDetail;
import com.poly.datn.be.entity.OrderStatus;
import com.poly.datn.be.repo.OrderRepo;
import com.poly.datn.be.service.AccountService;
import com.poly.datn.be.service.OrderDetailService;
import com.poly.datn.be.service.OrderService;
import com.poly.datn.be.service.OrderStatusService;
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
        return order;
    }
}
