package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.OrderStatus;
import com.poly.datn.be.repo.OrderStatusRepo;
import com.poly.datn.be.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    OrderStatusRepo orderStatusRepo;
    @Override
    public OrderStatus getById(Long id) {
        Optional<OrderStatus> optional = orderStatusRepo.findById(id);
        if(!optional.isPresent()){
            throw new AppException(AppConst.MSG_ERROR_COMMON_RESOURCE_NOT_VALID);
        }
        return optional.get();
    }
}
