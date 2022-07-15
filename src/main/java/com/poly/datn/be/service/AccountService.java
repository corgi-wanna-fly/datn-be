package com.poly.datn.be.service;

import com.poly.datn.be.domain.resp_dto.RespAccountDto;
import com.poly.datn.be.entity.Account;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountService {
    Account findById(Long id);

    List<RespAccountDto> findAllSecond(Pageable pageable);

    RespAccountDto findByIdSecond(Long id);

    List<Object[]> findByUsername(String username);

    void deleteOrRestore(Boolean isActive, Long id);

    List<Object[]> findAccountByIsActiveOrInactive(Boolean isActive, Pageable pageable);

    Account save(Account account);

    Account findAccountByUsername(String username);

    Integer getToTalPage();
}
