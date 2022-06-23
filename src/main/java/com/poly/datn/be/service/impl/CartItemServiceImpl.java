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
    public CartItem modifyCartItem(ReqCartItemDto reqCartItemDto) {
        Attribute attribute = attributeService.findById(reqCartItemDto.getAttributeId());
        Account account = accountService.findById(reqCartItemDto.getAccountId());
        CartItem cartItem = cartItemRepo.findCartItemByAccountIdAndAttributeId(reqCartItemDto.getAccountId(),
                reqCartItemDto.getAttributeId());
        if(cartItem == null){
            if(reqCartItemDto.getQuantity() > attribute.getStock()){
                throw new AppException(AppConst.CART_ITEM_MSG_ERROR_NOT_ENOUGH);
            }else{
                CartItem c = new CartItem();
                c.setAccount(account);
                c.setAttribute(attribute);
                c.setQuantity(AppConst.CART_ITEM_QUANTITY_ADD);
                return cartItemRepo.save(c);
            }
        }else{
            int flag = reqCartItemDto.getQuantity() + cartItem.getQuantity();
            if(flag == 0){
                cartItemRepo.delete(cartItem);
                return new CartItem();
            }else if(flag > attribute.getStock()){
                throw new AppException(AppConst.CART_ITEM_MSG_ERROR_NOT_ENOUGH);
            }else{
                cartItem.setQuantity(flag);
                return cartItemRepo.save(cartItem);
            }
        }
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

    @Override
    public void clearCartItem(Long id) {
        List<CartItem> cartItemList = getAllByAccountId(id);
        for(CartItem c: cartItemList){
            cartItemRepo.delete(c);
        }
    }

    @Override
    public Boolean isEnoughStock(Long id, Integer quantity) {
        Attribute attribute = attributeService.findById(id);
        if(attribute.getStock() < quantity){
            throw new AppException(AppConst.CART_ITEM_MSG_ERROR_NOT_ENOUGH);
        }
        return true;
    }
}
