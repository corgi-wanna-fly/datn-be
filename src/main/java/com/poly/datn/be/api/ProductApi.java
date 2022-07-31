package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.ProductConst;
import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.domain.dto.ReqProductDto;
import com.poly.datn.be.domain.dto.RespProductDto;
import com.poly.datn.be.domain.dto.ResponseProductDto;
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
    public ResponseEntity<?> getAllProductPagination(@RequestParam("page") Optional<Integer> page,
                                                     @RequestParam("size") Optional<Integer> size,
                                                     @RequestParam("active") Optional<Boolean> active) {
        Sort sort = Sort.by(Sort.Direction.DESC, "modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8), sort);
        return new ResponseEntity<>(productService.getProducts(active.orElse(true), pageable), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_GET_ALL_BY_BRAND)
    public ResponseEntity<?> getAllProductByBrand(@RequestParam("page") Optional<Integer> page,
                                                  @RequestParam("size") Optional<Integer> size,
                                                  @RequestParam("active") Optional<Boolean> active,
                                                  @RequestParam("brand") Long brand) {
        Sort sort = Sort.by(Sort.Direction.DESC, "modifyDate");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(8), sort);
        if (brand == 0) {
            return new ResponseEntity<>(productService.getProducts(active.orElse(true), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.getAllProductsByBrand(active.orElse(true), brand, pageable), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_SEARCH)
    public ResponseEntity<?> searchByKeyword(@RequestParam("page") Optional<Integer> page,
                                             @RequestParam("size") Optional<Integer> size,
                                             @RequestParam("keyword") String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(9), sort);
        return new ResponseEntity(productService.searchByKeyword("%" + keyword + "%", pageable), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ConvertUtil.fromProductDetail(productService.getProductById(id)), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_TOTAL_PAGE)
    public ResponseEntity<?> getTotalPage() {
        return new ResponseEntity<>(productService.getToTalPage(), HttpStatus.OK);
    }

    @GetMapping(ProductConst.API_PRODUCT_COUNT)
    public ResponseEntity<?> countProduct() {
        return new ResponseEntity<>(productService.countProduct(), HttpStatus.OK);
    }

    @PostMapping(ProductConst.API_PRODUCT_CREATE)
    public ResponseEntity<?> createProduct(@RequestBody ReqProductDto reqProductDto) {
        return new ResponseEntity<>(productService.create(reqProductDto), HttpStatus.OK);
    }
}
