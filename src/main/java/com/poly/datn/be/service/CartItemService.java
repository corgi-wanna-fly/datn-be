package com.poly.datn.be.service;

import java.util.List;

public interface CartItemService {
    List<Object[]> getCartItemByAccountId(Long id);
}
