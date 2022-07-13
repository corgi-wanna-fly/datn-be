package com.poly.datn.be.util;

import com.poly.datn.be.domain.dto.*;
import com.poly.datn.be.entity.Attribute;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.Product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {
    //mapper product -> resp-product-dto
    public static RespProductDto fromProduct(Object[] objects){
        RespProductDto respProductDto = new RespProductDto();
        respProductDto.setId((Long) objects[0]);
        respProductDto.setName((String) objects[1]);
        respProductDto.setCode((String) objects[2]);
        respProductDto.setDescription((String) objects[3]);
        respProductDto.setView((Long) objects[4]);
        respProductDto.setPrice((Double) objects[5]);
        respProductDto.setImageLink((String) objects[6]);
        respProductDto.setBrand((String) objects[7]);
        respProductDto.setDiscount((Integer) objects[8]);
        return respProductDto;
    }
//    public static RespBrandDto fromBrand(Object[] objects){
//        RespBrandDto respBrandDto = new RespBrandDto();
//        respBrandDto.setId((Long) objects[0]);
//        respBrandDto.setName((String) objects[1]);
//        respBrandDto.setDescription((String) objects[2]);
//        respBrandDto.setIsActive((Boolean) objects[3]);
//        respBrandDto.setImage((String) objects[4]);
//        return respBrandDto;
//    }

    public static RespProductDetailDto fromProductDetail(Product product){
        RespProductDetailDto respProductDetailDto = new RespProductDetailDto();
        respProductDetailDto.setId(product.getId());
        respProductDetailDto.setName(product.getName());
        respProductDetailDto.setCode(product.getCode());
        respProductDetailDto.setDescription(product.getDescription());
        String main = product.getImages().stream().filter(item -> item.getName().equals("main")).findFirst().get().getImageLink();
        respProductDetailDto.setMain(main);
        List<String> images = product.getImages().stream().map(item -> item.getImageLink()).collect(Collectors.toList());
        respProductDetailDto.setImages(images);
        respProductDetailDto.setAttributes((List<Attribute>) product.getAttributes());
        respProductDetailDto.setDiscount(product.getSale().getDiscount());
        return respProductDetailDto;
    }

    public static RespCartItemDto fromCartItem(Object[] objects){
        RespCartItemDto respCartItemDto = new RespCartItemDto();
        respCartItemDto.setId((Long) objects[0]);
        respCartItemDto.setImage((String) objects[1]);
        respCartItemDto.setName((String) objects[2]);
        respCartItemDto.setSize((Integer) objects[3]);
        respCartItemDto.setPrice((Double) objects[4]);
        respCartItemDto.setQuantity((Integer) objects[5]);
        respCartItemDto.setStock((Integer) objects[6]);
        respCartItemDto.setDiscount((Integer) objects[7]);
        respCartItemDto.setLastPrice((Double) objects[8]);
        return respCartItemDto;
    }

    public static Order fromReqOrderDto(ReqOrderDto reqOrderDto){
        Order order = new Order();
        order.setFullname(reqOrderDto.getFullname());
        order.setAddress(reqOrderDto.getAddress());
        order.setPhone(reqOrderDto.getPhone());
        order.setEmail(reqOrderDto.getEmail());
        order.setTotal(reqOrderDto.getTotal());
        order.setNote(reqOrderDto.getNote());
        order.setIsPending(reqOrderDto.getIsPending());
        order.setCreateDate(LocalDate.now());
        order.setModifyDate(LocalDate.now());
        order.setShipDate(Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return order;
    }
}
