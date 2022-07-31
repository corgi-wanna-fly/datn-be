package com.poly.datn.be.service.impl;

import com.poly.datn.be.entity.ProductCategory;
import com.poly.datn.be.repo.ProductCategoryRepo;
import com.poly.datn.be.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategory create(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
}
