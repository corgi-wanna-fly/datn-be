package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Product;
import com.poly.datn.be.repo.ProductRepo;
import com.poly.datn.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Object[]> getProducts(Pageable pageable) {
        return productRepo.getAllProducts(AppConst.PRODUCT_AVG_SIZE, AppConst.PRODUCT_MAIN_IMAGE, pageable);
    }

    @Override
    public Integer getToTalPage() {
        return productRepo.findAll(PageRequest.of(0, 9)).getTotalPages();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(!optionalProduct.isPresent()){
            throw new AppException("Product", "Id", "Not Exist");
        }
        return optionalProduct.get();
    }
}
