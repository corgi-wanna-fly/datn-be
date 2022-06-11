package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqOrderDto;
import com.poly.datn.be.entity.Order;

public interface OrderService {
    Order createOrder(ReqOrderDto reqOrderDto);
}
