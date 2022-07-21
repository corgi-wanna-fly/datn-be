package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.ProductConst;
import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.entity.Product;
import com.poly.datn.be.service.ProductService;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    ProductService productService;

    @GetMapping(ProductConst.API_PRODUCT_GET_ALL)
    public ResponseEntity<List<RespProductDto>> getAllProductPagination(@RequestParam("page")Optional<Integer> page,
                                                                  @RequestParam("size")Optional<Integer> size,
                                                                        @RequestParam("active")Optional<Boolean> active){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8), sort);
        List<Object[]> objects = productService.getProducts(active.orElse(true), pageable);
        List<RespProductDto> respProductDtoList = objects.stream().map(item -> ConvertUtil.fromProduct(item)).collect(Collectors.toList());
        return new ResponseEntity<>(respProductDtoList, HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_SEARCH)
    public ResponseEntity<?> searchByKeyword(@RequestParam("page")Optional<Integer> page,
                                            @RequestParam("size")Optional<Integer> size,
                                             @RequestParam("keyword") String keyword){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(9), sort);
        return new ResponseEntity(productService.searchByKeyword("%" + keyword + "%", pageable), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable("id")Long id){
        return new ResponseEntity<>(ConvertUtil.fromProductDetail(productService.getProductById(id)), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_TOTAL_PAGE)
    public ResponseEntity<?> getTotalPage(){
        return new ResponseEntity<>(productService.getToTalPage(), HttpStatus.OK);
    }

}
