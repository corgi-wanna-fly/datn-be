package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqCategoryDto;
import com.poly.datn.be.domain.dto.ReqCategoryProductDto;
import com.poly.datn.be.entity.Category;
import com.poly.datn.be.entity.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category saveCategory(ReqCategoryDto categoryDto);
    Category updateCategory(ReqCategoryDto categoryDto);
    Category deleteCategory(ReqCategoryDto categoryDto);
    ProductCategory createProductCate(ReqCategoryProductDto productDto);
}
