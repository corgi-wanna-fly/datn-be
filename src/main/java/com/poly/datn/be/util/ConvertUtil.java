package com.poly.datn.be.util;

import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;

public class ConvertUtil {
    //mapper product -> resp-product-dto
    public static RespProductDto fromProduct(Product product){
        RespProductDto respProductDto = new RespProductDto();
        respProductDto.setId(product.getId());
        respProductDto.setName(product.getName());
        respProductDto.setCode(product.getCode());
        respProductDto.setDescription(product.getDescription());
        respProductDto.setView(product.getView());
        return respProductDto;
    }
}
