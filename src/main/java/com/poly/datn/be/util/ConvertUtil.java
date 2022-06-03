package com.poly.datn.be.util;

import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;

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
}
