package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AccountConst;
import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.domain.req_dto.*;
import com.poly.datn.be.domain.resp_dto.RespAccountDto;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.AccountDetail;
import com.poly.datn.be.repo.AccountRepo;
import com.poly.datn.be.service.AccountDetailService;
import com.poly.datn.be.service.AccountService;
import com.poly.datn.be.util.ConvertUtil;
import com.poly.datn.be.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
            throw new AppException("T??i kho???n kh??ng t???n t???i");
        }else {
            Account account = optionalAccount.get();
            AccountDetail ad = this.accountDetailService.findAccountDetail(account.getId());
            if (account.getRole().getId() == 1 && !reqUpdateAccountDto.getIsActive()) {
                throw new AppException("Kh??ng th??? d???ng ho???t ?????ng t??i kho???n Admin");
            }
            if (
                    !reqUpdateAccountDto.getEmail().equals(ad.getEmail())
                            && this.accountDetailService.findAccountDetailByEmail(reqUpdateAccountDto.getEmail()) != null
            ) {
                throw new AppException("Email ???? t???n t???i");
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
            throw new AppException("Username ???? t???n t???i");
        }
        if (this.accountDetailService.findAccountDetailByEmail(reqCreateAccountDto.getEmail()) != null){
            throw new AppException("Email ???? t???n t???i");
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

    @Transactional
    @Modifying
    @Override
    public RespAccountDto register(ReqRegisterAccountDto reqRegisterAccountDto) {

        if (this.accountRepo.findAccountByUsername(reqRegisterAccountDto.getUsername()) != null) {
            throw new AppException("Username ???? t???n t???i");
        }
        if (this.accountDetailService.findAccountDetailByEmail(reqRegisterAccountDto.getEmail()) != null){
            throw new AppException("Email ???? t???n t???i");
        }
        Account account = ConvertUtil.ReqCreateAccountDtoToAccount(reqRegisterAccountDto);
        account.setId(this.accountRepo.save(account).getId());
        AccountDetail accountDetail = ConvertUtil.ReqAccountDtoToAccountDetail(reqRegisterAccountDto);
        accountDetail.setAccount(account);
        AccountDetail accountDetail1 = this.accountDetailService.save(accountDetail);
        RespAccountDto respAccountDto = ConvertUtil.accountToRespAccountDto(account, accountDetail1);
        return respAccountDto;
    }

//    @Override
//    public void changePassword(ReqChangePasswordDto reqChangePasswordDto) {
//        Account account = this.accountRepo.findAccountByUsername(reqChangePasswordDto.getUsername());
//        if (account == null){
//            throw new AppException("T??i kho???n kh??ng t???n t???i");
//        }
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        if (!passwordEncoder.matches(account.getPassword(), reqChangePasswordDto.getPassword())) {
//            throw new AppException("Password c?? kh??ng ????ng!");
//        } else if (!reqChangePasswordDto.getNewPassword().equals(reqChangePasswordDto.getNewPasswordSecond())) {
//            throw new AppException("Password m???i kh??ng gi???ng nhau");
//        }else {
//            account.setPassword(passwordEncoder.encode(reqChangePasswordDto.getNewPassword().trim()));
//            this.accountRepo.save(account);
//        }
//    }
//
//    @Transactional
//    @Override
//    public void forgotPassword(ReqForgotPasswordDto reqForgotPasswordDto) throws MessagingException {
//        Account account = this.accountRepo.findAccountByUsername(reqForgotPasswordDto.getUsername());
//        if (account == null){
//            throw new AppException("Username kh??ng t???n t???i");
//        }else {
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String newPassword = String.valueOf(UUID.randomUUID());
//            account.setPassword(passwordEncoder.encode(newPassword));
//            this.accountRepo.save(account);
//            //g???i mail
//            AccountDetail accountDetail = this.accountDetailService.findAccountDetail(account.getId());
//            MailUtil.sendmailForgotPassword(accountDetail.getEmail(), newPassword);
//        }
//    }


}
