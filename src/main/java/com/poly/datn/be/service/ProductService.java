package com.poly.datn.be.service;

import com.poly.datn.be.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(Pageable pageable);
}
