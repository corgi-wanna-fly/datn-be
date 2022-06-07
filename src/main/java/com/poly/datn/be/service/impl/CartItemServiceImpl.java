package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.dto.ReqCartItemDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.Attribute;
import com.poly.datn.be.entity.CartItem;
import com.poly.datn.be.repo.CartItemRepo;
import com.poly.datn.be.service.AccountService;
import com.poly.datn.be.service.AttributeService;
import com.poly.datn.be.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    AttributeService attributeService;
    @Autowired
    AccountService accountService;

    @Override
    public List<Object[]> getCartItemByAccountId(Long id) {
        return cartItemRepo.getCartItemByAccountId(id, AppConst.PRODUCT_MAIN_IMAGE);
    }

    @Override
    public List<CartItem> getAllByAccountId(Long id) {
        return cartItemRepo.findAll();
    }

    @Override
    public CartItem addCartItem(ReqCartItemDto reqCartItemDto) {
        Attribute attribute = attributeService.findById(reqCartItemDto.getAttributeId());
        Account account = accountService.findById(reqCartItemDto.getAccountId());
        CartItem cartItem = new CartItem();
        cartItem.setAttribute(attribute);
        cartItem.setAccount(account);
        cartItem.setQuantity(AppConst.CART_ITEM_QUANTITY_ADD);
        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem modifyCartItem(ReqCartItemDto reqCartItemDto) {
        CartItem cartItem = cartItemRepo.findCartItemByAccountIdAndAttributeId(reqCartItemDto.getAccountId(),
                reqCartItemDto.getAttributeId());
        if(cartItem == null){
            throw new AppException(AppConst.MSG_ERROR_COMMON_RESOURCE_NOT_VALID);
        }
        cartItem.setQuantity(reqCartItemDto.getQuantity());
       return cartItemRepo.save(cartItem);
    }

    @Override
    public void removeCartItem(ReqCartItemDto reqCartItemDto) {
        CartItem cartItem = cartItemRepo.findCartItemByAccountIdAndAttributeId(reqCartItemDto.getAccountId(),
                reqCartItemDto.getAttributeId());
        if(cartItem == null){
            throw new AppException(AppConst.MSG_ERROR_COMMON_RESOURCE_NOT_VALID);
        }
        cartItemRepo.delete(cartItem);
    }
}
