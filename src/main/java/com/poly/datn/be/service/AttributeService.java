package com.poly.datn.be.service;

import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.domain.dto.RespAttributeDto;
import com.poly.datn.be.entity.Attribute;

import java.util.List;

public interface AttributeService {
    Attribute findById(Long id);

    List<RespAttributeDto> cacheAttribute(List<ReqCacheAttributeDto> reqCacheAttributeDtoList);

    List<RespAttributeDto> backAttribute(List<ReqCacheAttributeDto> reqCacheAttributeDtoList);
}
