package com.poly.datn.be.service.impl;

import com.poly.datn.be.entity.AccountDetail;
import com.poly.datn.be.repo.AccountDetailRepo;
import com.poly.datn.be.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    AccountDetailRepo accountDetailRepo;
    @Override
    public AccountDetail findAccountDetail(Long id) {
        return accountDetailRepo.findAccountDetailByAccount_Id(id);
    }
}
