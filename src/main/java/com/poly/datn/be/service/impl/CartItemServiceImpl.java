package com.poly.datn.be.service.impl;

import com.poly.datn.be.repo.CartItemRepo;
import com.poly.datn.be.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;

    @Override
    public List<Object[]> getCartItemByAccountId(Long id) {
        return cartItemRepo.getCartItemByAccountId(id, "main");
    }
}
