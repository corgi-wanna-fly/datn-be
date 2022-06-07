package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Attribute;
import com.poly.datn.be.repo.AttributeRepo;
import com.poly.datn.be.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    AttributeRepo attributeRepo;
    @Override
    public Attribute findById(Long id) {
        Optional<Attribute> optionalAttribute = attributeRepo.findById(id);
        if(!optionalAttribute.isPresent()){
            throw new AppException(AppConst.ATTRIBUTE_MSG_ERROR_NOT_EXIST);
        }
        return optionalAttribute.get();
    }
}
