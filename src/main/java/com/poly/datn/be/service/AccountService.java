package com.poly.datn.be.service;

import com.poly.datn.be.domain.req_dto.ReqCreateAccountDto;
import com.poly.datn.be.domain.req_dto.ReqUpdateAccountDto;
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

    RespAccountDto findByUsername(String username);

    void deleteOrRestore(Boolean isActive, Long id);

    List<Object[]> findAccountByIsActiveOrInactive(Boolean isActive, Pageable pageable);

    Account update(ReqUpdateAccountDto reqUpdateAccountDto);

    Account save(ReqCreateAccountDto reqCreateAccountDto);

    Account findAccountByUsername(String username);

    Integer getToTalPage();

    List<RespAccountDto> findAccountByRoleName(String roleName, Pageable pageable);
}
