package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;
import com.poly.datn.be.service.ProductService;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    ProductService productService;

    @GetMapping(AppConst.API_PRODUCT_GET_ALL)
    public ResponseEntity<List<RespProductDto>> getAllProductPagination(@RequestParam("page")Optional<Integer> page,
                                                                  @RequestParam("size")Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8));
        List<Object[]> objects = productService.getProducts(pageable);
        List<RespProductDto> respProductDtoList = objects.stream().map(item -> ConvertUtil.fromProduct(item)).collect(Collectors.toList());
        return new ResponseEntity<>(respProductDtoList, HttpStatus.OK);
    }

    @GetMapping(AppConst.API_PRODUCT_TOTAL_PAGE)
    public ResponseEntity<?> getTotalPage(){
        return new ResponseEntity<>(productService.getToTalPage(), HttpStatus.OK);
    }
}
