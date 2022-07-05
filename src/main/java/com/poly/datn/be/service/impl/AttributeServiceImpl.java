package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.AttributeConst;
import com.poly.datn.be.domain.dto.ReqCacheAttributeDto;
import com.poly.datn.be.domain.dto.RespAttributeDto;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Attribute;
import com.poly.datn.be.repo.AttributeRepo;
import com.poly.datn.be.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    AttributeRepo attributeRepo;
    @Override
    public Attribute findById(Long id) {
        Optional<Attribute> optionalAttribute = attributeRepo.findById(id);
        if(!optionalAttribute.isPresent()){
            throw new AppException(AttributeConst.ATTRIBUTE_MSG_ERROR_NOT_EXIST);
        }
        return optionalAttribute.get();
    }

    @Override
    @Transactional
    public List<RespAttributeDto> cacheAttribute(List<ReqCacheAttributeDto> list) {
        List<RespAttributeDto> respAttributeDtoList = new ArrayList<>();
        for(ReqCacheAttributeDto reqCacheAttributeDto: list){
            Optional<Attribute> optionalAttribute = attributeRepo.findById(reqCacheAttributeDto.getAttributeId());
            if(!optionalAttribute.isPresent()){
                throw new AppException(AppConst.MSG_ERROR_COMMON_RESOURCE_NOT_VALID);
            }else {
                Attribute attribute = optionalAttribute.get();
                if(reqCacheAttributeDto.getQuantity() > attribute.getStock()){
                    throw new AppException(AttributeConst.ATTRIBUTE_MSG_ERROR_NOT_ENOUGH_STOCK);
                }else{
                    attribute.setStock(attribute.getStock() - reqCacheAttributeDto.getQuantity());
                    attribute.setCache(attribute.getCache() + reqCacheAttributeDto.getQuantity());
                    attributeRepo.save(attribute);
                    respAttributeDtoList.add(new RespAttributeDto(attribute.getId(), attribute.getStock(), attribute.getCache()));
                }
            }
        }
        return respAttributeDtoList;
    }

    @Override
    @Transactional
    public List<RespAttributeDto> backAttribute(List<ReqCacheAttributeDto> list) {
        List<RespAttributeDto> respAttributeDtoList = new ArrayList<>();
        for(ReqCacheAttributeDto reqCacheAttributeDto: list){
            Optional<Attribute> optionalAttribute = attributeRepo.findById(reqCacheAttributeDto.getAttributeId());
            if(!optionalAttribute.isPresent()){
                throw new AppException(AppConst.MSG_ERROR_COMMON_RESOURCE_NOT_VALID);
            }else {
                Attribute attribute = optionalAttribute.get();
                if(reqCacheAttributeDto.getQuantity() > attribute.getCache()){
                    throw new AppException(AttributeConst.ATTRIBUTE_MSG_ERROR_NOT_ENOUGH_STOCK);
                }else{
                    attribute.setStock(attribute.getStock() + reqCacheAttributeDto.getQuantity());
                    attribute.setCache(attribute.getCache() - reqCacheAttributeDto.getQuantity());
                    attributeRepo.save(attribute);
                    respAttributeDtoList.add(new RespAttributeDto(attribute.getId(), attribute.getStock(), attribute.getCache()));
                }
            }
        }
        return respAttributeDtoList;
    }

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepo.save(attribute);
    }
}
