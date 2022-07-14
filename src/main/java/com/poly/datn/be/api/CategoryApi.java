package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.CategoryConst;
import com.poly.datn.be.domain.dto.ReqCategoryDto;
import com.poly.datn.be.domain.dto.ReqCategoryProductDto;
import com.poly.datn.be.entity.Category;
import com.poly.datn.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(CategoryConst.API_CATEGORY)
public class CategoryApi {

    final
    CategoryService categoryService;

    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(CategoryConst.FIND_ALL)
    public ResponseEntity<?> findAllList() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping(CategoryConst.CREATE)
    public ResponseEntity<?> createCategory(@RequestBody ReqCategoryDto categoryDto) {
         return ResponseEntity.ok(categoryService.saveCategory(categoryDto));
    }

    @PostMapping(CategoryConst.UPDATE)
    public ResponseEntity<?> updateCategory(@RequestBody ReqCategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));
    }

    @PostMapping(CategoryConst.DELETE)
    public ResponseEntity<?> deleteCategory(@RequestBody ReqCategoryDto categoryDto){
             return ResponseEntity.ok(categoryService.deleteCategory(categoryDto));
    }

    @PostMapping(CategoryConst.ADD_PRODUCT_CATEGORY)
    public ResponseEntity<?> addProductToCategory(@RequestBody ReqCategoryProductDto productDto){
        return ResponseEntity.ok(categoryService.createProductCate(productDto));
    }

}
