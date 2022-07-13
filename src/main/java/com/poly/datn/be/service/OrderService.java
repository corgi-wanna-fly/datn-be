package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.domain.dto.ReqUpdateOrderDto;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Order createOrder(ReqOrderDto reqOrderDto) ;
    Page<Order> getOrderByAccount(Long id, Pageable pageable);
    Order getByOrderId(Long id);
    List<OrderDetail> getAllByOrderId(Long id);
    Page<Order> findOrderByAccountIdAndOrderStatusId(Long accountId, Long orderStatusId, Pageable pageable);
    Page<Order> getAllOrdersAndPagination(Long id, Pageable pageable);
    Order updateOrderWithStatus(Long orderId, Long statusId);
    Order updateOrder(ReqUpdateOrderDto reqUpdateOrderDto);
    Order cancelOrder(Long orderId);
    Page<Order> findOrderByAccount_Id(Long id, Pageable pageable);
}
