package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.AttributeConst;
import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AttributeApi {
    @Autowired
    AttributeService attributeService;
    @GetMapping(AttributeConst.API_ATTRIBUTE_GET_BY_PRODUCT_ID)
    public ResponseEntity<?> cacheAttribute(@RequestParam("id") Long id,
                                            @RequestParam("size") Optional<Integer> size){
        return new ResponseEntity<>(attributeService.getByProductIdAndSize(id, size.orElse(39)), HttpStatus.OK);
    }

}
