package com.poly.datn.be.service.impl;

import com.poly.datn.be.entity.Product;
import com.poly.datn.be.repo.ProductRepo;
import com.poly.datn.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getProducts(Pageable pageable) {
        return productRepo.findAll(pageable).getContent();
    }
}
