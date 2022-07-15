package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AccountConst;
import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.repo.AccountRepo;
import com.poly.datn.be.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Override
    public Account findById(Long id) {
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if(!optionalAccount.isPresent()){
            throw new AppException(AccountConst.ACCOUNT_MSG_ERROR_NOT_EXIST);
        }
        return optionalAccount.get();
    }

    @Override
    public List<Object[]> findAllSecond(Pageable pageable) {
        return this.accountRepo.findAllSecond(pageable);
    }

    @Override
    public List<Object[]> findByIdSecond(Long id) {
        return this.accountRepo.findByIdSecond(id);
    }

    @Override
    public List<Object[]> findByUsername(String username) {
        return this.accountRepo.findByUsername(username);
    }

    @Override
    public void deleteOrRestore(Boolean isActive, Long id) {
        this.accountRepo.deleteOrRestore(isActive, id);
    }

    @Override
    public List<Object[]> findAccountByIsActiveOrInactive(Boolean isActive, Pageable pageable) {
        return this.accountRepo.findAccountByIsActiveOrInactive(isActive, pageable);
    }

    @Override
    public Account save(Account account) {
        return this.accountRepo.save(account);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return this.accountRepo.findAccountByUsername(username);
    }

}
