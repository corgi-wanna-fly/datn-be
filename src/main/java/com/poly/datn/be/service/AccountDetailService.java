package com.poly.datn.be.service;

import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.AccountDetail;

public interface AccountDetailService {
    AccountDetail findAccountDetail(Long id);

    AccountDetail save(AccountDetail accountDetail);

    void update(AccountDetail accountDetail);

    AccountDetail findAccountDetailByEmail(String email);
}
