package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AttributeApi {
    @Autowired
    AttributeService attributeService;

    @PostMapping(AppConst.API_ATTRIBUTE_CACHE)
    public ResponseEntity<?> cacheAttribute(@RequestBody List<ReqCacheAttributeDto> reqCacheAttributeDtoList){
        return new ResponseEntity<>(attributeService.cacheAttribute(reqCacheAttributeDtoList), HttpStatus.OK);
    }

    @PostMapping(AppConst.API_ATTRIBUTE_BACK)
    public ResponseEntity<?> backAttribute(@RequestBody List<ReqCacheAttributeDto> reqCacheAttributeDtoList){
        return new ResponseEntity<>(attributeService.backAttribute(reqCacheAttributeDtoList), HttpStatus.OK);
    }
}
