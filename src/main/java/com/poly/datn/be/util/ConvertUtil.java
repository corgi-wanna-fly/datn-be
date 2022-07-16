package com.poly.datn.be.util;

import com.poly.datn.be.domain.dto.*;
import com.poly.datn.be.domain.req_dto.ReqCreateAccountDto;
import com.poly.datn.be.domain.req_dto.ReqUpdateAccountDto;
import com.poly.datn.be.domain.resp_dto.RespAccountDto;
import com.poly.datn.be.entity.*;

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
        respProductDto.setImage((String) objects[6]);
        respProductDto.setBrand((String) objects[7]);
        respProductDto.setDiscount((Integer) objects[8]);
        respProductDto.setIsActive((Boolean) objects[9]);
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

    public static RespAccountDto accountToRespAccountDto(Object[] objects){
        RespAccountDto respAccountDto = new RespAccountDto();
        respAccountDto.setId((Long) objects[0]);
        respAccountDto.setUsername((String) objects[1]);
        respAccountDto.setCreateDate((LocalDate) objects[2]);
        respAccountDto.setModifyDate((LocalDate) objects[3]);
        respAccountDto.setIsActive((Boolean) objects[4]);
        respAccountDto.setRoleName((String) objects[5]);
        respAccountDto.setFullName((String) objects[6]);
        respAccountDto.setGender((String) objects[7]);
        respAccountDto.setPhone((String) objects[8]);
        respAccountDto.setEmail((String) objects[9]);
        respAccountDto.setAddress((String) objects[10]);
        respAccountDto.setBirthDate((Date) objects[11]);
        return  respAccountDto;
    }

    public static Account ReqCreateAccountDtoToAccount(ReqCreateAccountDto reqAccountDto){
        Account account = new Account();
        account.setUsername(reqAccountDto.getUsername());
        account.setPassword(reqAccountDto.getPassword());
        account.setCreateDate(LocalDate.now());
        account.setModifyDate(LocalDate.now());
        account.setIsActive(true);
        Role role = new Role();
        role.setId(reqAccountDto.getRoleId());
        account.setRole(role);
        return account;
    }

    public static Account ReqUpdateAccountDtoToAccount(Account account, ReqUpdateAccountDto reqUpdateAccountDto){
        account.setId(reqUpdateAccountDto.getId());
        account.setPassword(reqUpdateAccountDto.getPassword());
        account.setIsActive(reqUpdateAccountDto.getIsActive());
        Role role = new Role();
        role.setId(reqUpdateAccountDto.getRoleId());
        account.setRole(role);
        return account;
    }

    public static AccountDetail ReqAccountDtoToAccountDetail(ReqCreateAccountDto reqCreateAccountDto){
        AccountDetail accountDetail = new AccountDetail();
        Account account = new Account();
        accountDetail.setAccount(account);
        accountDetail.setFullname(reqCreateAccountDto.getFullName());
        accountDetail.setGender(reqCreateAccountDto.getGender());
        accountDetail.setPhone(reqCreateAccountDto.getPhone());
        accountDetail.setEmail(reqCreateAccountDto.getEmail());
        accountDetail.setAddress(reqCreateAccountDto.getAddress());
        accountDetail.setBirthDate(reqCreateAccountDto.getBirthDate());
        return accountDetail;
    }

    public static AccountDetail ReqAccountDtoToAccountDetail(ReqUpdateAccountDto ReqUpdateAccountDto){
        AccountDetail accountDetail = new AccountDetail();
        Account account = new Account();
        account.setId(ReqUpdateAccountDto.getId());
        accountDetail.setAccount(account);
        accountDetail.setFullname(ReqUpdateAccountDto.getFullName());
        accountDetail.setGender(ReqUpdateAccountDto.getGender());
        accountDetail.setPhone(ReqUpdateAccountDto.getPhone());
        accountDetail.setEmail(ReqUpdateAccountDto.getEmail());
        accountDetail.setAddress(ReqUpdateAccountDto.getAddress());
        accountDetail.setBirthDate(ReqUpdateAccountDto.getBirthDate());
        return accountDetail;
    }
}
