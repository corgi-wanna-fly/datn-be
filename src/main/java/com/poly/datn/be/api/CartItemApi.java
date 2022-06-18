package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.dto.ReqCartItemDto;
import com.poly.datn.be.domain.dto.RespCartItemDto;
import com.poly.datn.be.service.CartItemService;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
public class CartItemApi {
    @Autowired
    CartItemService cartItemService;
    @GetMapping(AppConst.API_CART_ITEM_GET_BY_ACCOUNT_ID)
    public ResponseEntity<?> getCartItemByAccountId(@PathVariable("id") Long id){
        List<RespCartItemDto> cartItemDtoList = cartItemService.getCartItemByAccountId(id).stream()
                .map(item -> ConvertUtil.fromCartItem(item)).collect(Collectors.toList());
        return new ResponseEntity<>(cartItemDtoList, HttpStatus.OK);
    }

    @PostMapping(AppConst.API_CART_ITEM_MODIFY)
    public ResponseEntity<?> modifyCartItem(@RequestBody ReqCartItemDto reqCartItemDto){
        return new ResponseEntity<>(cartItemService.modifyCartItem(reqCartItemDto), HttpStatus.OK);
    }

    @PostMapping(AppConst.API_CART_ITEM_REMOVE)
    public ResponseEntity<?> removeCartItem(@RequestBody ReqCartItemDto reqCartItemDto){
        cartItemService.removeCartItem(reqCartItemDto);
        return new ResponseEntity<>(AppConst.MSG_SUCCESS_COMMON, HttpStatus.OK);
    }
}
