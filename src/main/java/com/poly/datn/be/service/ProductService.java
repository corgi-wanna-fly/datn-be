package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Object[]> getProducts(Pageable pageable);

    Integer getToTalPage();

    Product getProductById(Long id);
}
