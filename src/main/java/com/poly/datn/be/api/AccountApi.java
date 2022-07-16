package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AccountConst;
import com.poly.datn.be.domain.constant.ProductConst;
import com.poly.datn.be.domain.req_dto.ReqCreateAccountDto;
import com.poly.datn.be.domain.req_dto.ReqUpdateAccountDto;
import com.poly.datn.be.domain.resp_dto.RespAccountDto;
import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.AccountDetail;
import com.poly.datn.be.service.AccountDetailService;
import com.poly.datn.be.service.AccountService;
import com.poly.datn.be.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class AccountApi {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDetailService accountDetailService;


    @GetMapping(AccountConst.API_ACCOUNT_FIND_ALL)
    public ResponseEntity<?> findAll(@RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(9), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(this.accountService.findAllSecond(pageable), HttpStatus.OK);
    }

    @GetMapping(AccountConst.API_ACCOUNT_FIND_BY_ID)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.accountService.findByIdSecond(id), HttpStatus.OK);
    }

    @GetMapping(AccountConst.API_ACCOUNT_FIND_BY_USERNAME)
    public ResponseEntity<?> findByUsername(@RequestParam("username") String username) {
        return new ResponseEntity<>(this.accountService.findByUsername(username), HttpStatus.OK);
    }

//    @GetMapping(AccountConst.API_ACCOUNT_DELETE_OR_RESTORE)
//    public ResponseEntity<?> deleteOrRestore(@PathVariable("id") Long id, @RequestParam("isActive") Optional<Boolean> isActive) {
//        this.accountService.deleteOrRestore(isActive.orElse(false), id);
//        return new ResponseEntity<>("Update Successfully", HttpStatus.OK);
//    }

    @GetMapping(AccountConst.API_ACCOUNT_FIND_ALL_BY_IS_ACTIVE_OR_INACTIVE)
    public ResponseEntity<?> findAccountByIsActiveOrInactive(@PathVariable("isActive") Boolean isActive,
                                                             @RequestParam("page") Optional<Integer> page,
                                                             @RequestParam("size") Optional<Integer> size
    ) {
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(9));
        List<Object[]> objects = this.accountService.findAccountByIsActiveOrInactive(isActive, pageable);
        List<RespAccountDto> respAccountDtos = objects.stream().map(item -> ConvertUtil.accountToRespAccountDto(item))
                .sorted((o1, o2) -> o1.getId() > o2.getId() ? -1 : 1)
                .collect(Collectors.toList());
        return new ResponseEntity<>(respAccountDtos, HttpStatus.OK);
    }

    @Transactional
    @Modifying
    @PostMapping(AccountConst.API_ACCOUNT_CREATE)
    public ResponseEntity<?> create(@RequestBody @Valid ReqCreateAccountDto reqCreateAccountDto) {
        if (this.accountService.findAccountByUsername(reqCreateAccountDto.getUsername()) != null ||
                this.accountDetailService.findAccountDetailByEmail(reqCreateAccountDto.getEmail()) != null
        ) {
            return new ResponseEntity<>("Username hoặc Email đã tồn tại!", HttpStatus.FOUND);
        }
        Account account = ConvertUtil.ReqCreateAccountDtoToAccount(reqCreateAccountDto);
        account.setId(this.accountService.save(account).getId());
        AccountDetail accountDetail = ConvertUtil.ReqAccountDtoToAccountDetail(reqCreateAccountDto);
        accountDetail.setAccount(account);
        this.accountDetailService.save(accountDetail);
        return new ResponseEntity<>("Create Successfully", HttpStatus.OK);
    }

    @Transactional
    @Modifying
    @PostMapping(AccountConst.API_ACCOUNT_UPDATE)
    public ResponseEntity<?> update(@RequestBody @Valid ReqUpdateAccountDto reqUpdateAccountDto) {
        Account account = this.accountService.findById(reqUpdateAccountDto.getId());
        AccountDetail ad = this.accountDetailService.findAccountDetail(account.getId());
        if (account.getRole().getId() == 1) {
            return new ResponseEntity<>("Không thể thay đổi quyền Admin", HttpStatus.FOUND);
        }
        if (account == null) {
            return new ResponseEntity<>("Mã id Account không tồn tại!", HttpStatus.NOT_FOUND);
        }
        if (
                !reqUpdateAccountDto.getEmail().equals(ad.getEmail()) && this.accountDetailService.findAccountDetailByEmail(reqUpdateAccountDto.getEmail()) != null
        ) {
            return new ResponseEntity<>("Email đã tồn tại!", HttpStatus.FOUND);
        }
        account = ConvertUtil.ReqUpdateAccountDtoToAccount(account, reqUpdateAccountDto);
        this.accountService.save(account);
        AccountDetail accountDetail = ConvertUtil.ReqAccountDtoToAccountDetail(reqUpdateAccountDto);
        this.accountDetailService.update(accountDetail);
        return new ResponseEntity<>("Update Successfully", HttpStatus.OK);
    }

    @GetMapping(AccountConst.API_ACCOUNT_TOTAL_PAGE)
    public ResponseEntity<?> getTotalPage() {
        return new ResponseEntity<>(this.accountService.getToTalPage(), HttpStatus.OK);
    }
}
