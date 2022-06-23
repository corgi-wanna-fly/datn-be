package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    Order createOrder(ReqOrderDto reqOrderDto);
    List<Order> getOrderByAccount(Long id);
    Order getByOrderId(Long id);
    List<OrderDetail> getAllByOrderId(Long id);
}
