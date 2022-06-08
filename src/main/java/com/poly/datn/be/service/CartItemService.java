package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqCartItemDto;
import com.poly.datn.be.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<Object[]> getCartItemByAccountId(Long id);
    List<CartItem> getAllByAccountId(Long id);
    CartItem modifyCartItem(ReqCartItemDto reqCartItemDto);
    void removeCartItem(ReqCartItemDto reqCartItemDto);
}
