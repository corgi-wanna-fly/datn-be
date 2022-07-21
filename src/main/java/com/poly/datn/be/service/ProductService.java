package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Object[]> getProducts(Boolean active, Pageable pageable);
    Integer getToTalPage();
    List<RespProductDto> searchByKeyword(String keyword, Pageable pageable);
    Product getProductById(Long id);
    List<Product> getProductByBrand(Long id);
    List<Product> getProductByCategory(Long id);
    List<Product> getProductBySale(Long id);
    Product update(Product product);
}
