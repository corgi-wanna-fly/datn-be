package com.poly.datn.be.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.datn.be.domain.constant.CategoryConst;
import com.poly.datn.be.domain.constant.OrderConst;
import com.poly.datn.be.domain.constant.OrderStatusConst;
import com.poly.datn.be.domain.dto.ReqCategoryDto;
import com.poly.datn.be.domain.dto.ReqCategoryProductDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Category;
import com.poly.datn.be.entity.Order;
import com.poly.datn.be.entity.Product;
import com.poly.datn.be.entity.ProductCategory;
import com.poly.datn.be.repo.CategoryRepo;
import com.poly.datn.be.repo.ProductCategoryRepo;
import com.poly.datn.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    final
    CategoryRepo categoryRepo;

    final
    ProductCategoryRepo productCategoryRepo;

    final
    ObjectMapper objectMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ObjectMapper objectMapper, ProductCategoryRepo productCategoryRepo) {
        this.categoryRepo = categoryRepo;
        this.objectMapper = objectMapper;
        this.productCategoryRepo = productCategoryRepo;
    }

    @Override
    @Transactional
    public Page<Category> getListCategory(Pageable pageable) {
        return categoryRepo.categoryList(pageable);
    }

    @Override
    @Transactional
    public Category saveCategory(ReqCategoryDto categoryDto) {
        try {
            Clock clock = Clock.systemDefaultZone();
            LocalDate localDate = LocalDate.now(clock);
            Category category = objectMapper.convertValue(categoryDto, Category.class);
            category.setCreateDate(localDate);
            category.setIsActive(true);
            return categoryRepo.save(category);
        } catch (Exception e) {
            throw new AppException(CategoryConst.FALSE);
        }
    }


    @Override
    @Transactional
    public Category updateCategory(ReqCategoryDto categoryDto) {
        try {
            Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getId());
            Category category = optionalCategory.get();
            Clock clock = Clock.systemDefaultZone();
            LocalDate localDate = LocalDate.now(clock);
            category.setDescription(categoryDto.getDescription());
            category.setName(categoryDto.getName());
            category.setModifyDate(localDate);
            return categoryRepo.save(category);
        } catch (Exception e) {
            throw new AppException(CategoryConst.FALSE);
        }
    }

    @Override
    @Transactional
    public Category deleteCategory(ReqCategoryDto categoryDto) {
        try {
            Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getId());
            Category category = optionalCategory.get();
            category.setIsActive(false);
            return categoryRepo.save(category);
        } catch (Exception e) {
            throw new AppException(CategoryConst.FALSE);
        }
    }

    @Override
    @Transactional
    public ProductCategory createProductCate(ReqCategoryProductDto productDto) {
        try {
            ProductCategory category = objectMapper.convertValue(productDto, ProductCategory.class);
            Category cate = new Category();
            cate.setId(productDto.getCategoryId());
            category.setCategory(cate);
            Product pro = new Product();
            pro.setId(productDto.getProductId());
            category.setProduct(pro);
            return productCategoryRepo.save(category);
        } catch (Exception e) {
            throw new AppException(CategoryConst.FALSE);
        }
    }
}
