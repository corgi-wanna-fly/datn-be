package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AccountConst;
import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.domain.req_dto.ReqCreateAccountDto;
import com.poly.datn.be.domain.req_dto.ReqUpdateAccountDto;
import com.poly.datn.be.domain.resp_dto.RespAccountDto;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.AccountDetail;
import com.poly.datn.be.repo.AccountRepo;
import com.poly.datn.be.service.AccountDetailService;
import com.poly.datn.be.service.AccountService;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountDetailService accountDetailService;

    @Override
    public Account findById(Long id) {
        Optional<Account> optionalAccount = accountRepo.findById(id);
        if(!optionalAccount.isPresent()){
            throw new AppException(AccountConst.ACCOUNT_MSG_ERROR_NOT_EXIST);
        }
        return optionalAccount.get();
    }

    @Override
    public List<RespAccountDto> findAllSecond(Pageable pageable) {
        return this.accountRepo.findAllSecond(pageable);
    }

    @Override
    public RespAccountDto findByIdSecond(Long id) {
        return this.accountRepo.findByIdSecond(id);
    }

    @Override
    public RespAccountDto findByUsername(String username) {
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

    @Transactional
    @Modifying
    @Override
    public Account update(ReqUpdateAccountDto reqUpdateAccountDto) {
        Optional<Account> optionalAccount = this.accountRepo.findById(reqUpdateAccountDto.getId());
        if (!optionalAccount.isPresent()) {
            throw new AppException("Tài khoản không tồn tại");
        }else {
            Account account = optionalAccount.get();
            AccountDetail ad = this.accountDetailService.findAccountDetail(account.getId());
            if (account.getRole().getId() == 1 && !reqUpdateAccountDto.getIsActive()) {
                throw new AppException("Không thể dừng hoạt động tài khoản Admin");
            }
            if (
                    !reqUpdateAccountDto.getEmail().equals(ad.getEmail())
                            && this.accountDetailService.findAccountDetailByEmail(reqUpdateAccountDto.getEmail()) != null
            ) {
                throw new AppException("Email đã tồn tại");
            }
            account = ConvertUtil.ReqUpdateAccountDtoToAccount(account, reqUpdateAccountDto);
            account = this.accountRepo.save(account);
            AccountDetail accountDetail = ConvertUtil.ReqAccountDtoToAccountDetail(reqUpdateAccountDto);
            this.accountDetailService.update(accountDetail);
            return account;
        }
    }

    @Transactional
    @Modifying
    @Override
    public Account save(ReqCreateAccountDto reqCreateAccountDto) {
        if (this.accountRepo.findAccountByUsername(reqCreateAccountDto.getUsername()) != null) {
            throw new AppException("Username đã tồn tại");
        }
        if (this.accountDetailService.findAccountDetailByEmail(reqCreateAccountDto.getEmail()) != null){
            throw new AppException("Email đã tồn tại");
        }
        Account account = ConvertUtil.ReqCreateAccountDtoToAccount(reqCreateAccountDto);
        account.setId(this.accountRepo.save(account).getId());
        AccountDetail accountDetail = ConvertUtil.ReqAccountDtoToAccountDetail(reqCreateAccountDto);
        accountDetail.setAccount(account);
        this.accountDetailService.save(accountDetail);
        return account;
    }

    @Override
    public Account findAccountByUsername(String username) {
        return this.accountRepo.findAccountByUsername(username);
    }

    @Override
    public Integer getToTalPage() {
        return this.accountRepo.findAll(PageRequest.of(0, 9)).getTotalPages();
    }

    @Override
    public List<RespAccountDto> findAccountByRoleName(String roleName, Pageable pageable) {
        return this.accountRepo.findAccountByRoleName(roleName, pageable);
    }


}
