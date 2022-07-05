package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.VoucherConst;
import com.poly.datn.be.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class VoucherApi {
    @Autowired
    VoucherService voucherService;
    @GetMapping(VoucherConst.API_VOUCHER_GET_BY_CODE)
    public ResponseEntity getVoucherByCode(@RequestParam("code") String code){
        return new ResponseEntity(voucherService.getVoucherByCode(code), HttpStatus.OK);
    }
}
