package com.poly.datn.be.repo;

import com.poly.datn.be.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepo extends JpaRepository<AccountDetail, Long> {
    AccountDetail findAccountDetailByAccount_Id(Long id);
}
