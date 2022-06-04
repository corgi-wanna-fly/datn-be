package com.poly.datn.be.util;

import com.poly.datn.be.domain.dto.RespProductDetailDto;
import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Attribute;
import com.poly.datn.be.entity.Product;

import java.util.Arrays;
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
        return respProductDto;
    }

    public static RespProductDetailDto fromProductDetail(Product product){
        RespProductDetailDto respProductDetailDto = new RespProductDetailDto();
        respProductDetailDto.setId(product.getId());
        respProductDetailDto.setName(product.getName());
        respProductDetailDto.setCode(product.getCode());
        String main = product.getImages().stream().filter(item -> item.getName().equals("main")).findFirst().get().getImageLink();
        respProductDetailDto.setMain(main);
        List<String> images = product.getImages().stream().map(item -> item.getImageLink()).collect(Collectors.toList());
        respProductDetailDto.setImages(images);
        respProductDetailDto.setAttributes((List<Attribute>) product.getAttributes());
        return respProductDetailDto;
    }
}
