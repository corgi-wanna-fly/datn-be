package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.VoucherConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Voucher;
import com.poly.datn.be.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class VoucherApi {
    @Autowired
    VoucherService voucherService;
    @GetMapping(VoucherConst.API_VOUCHER_GET_BY_CODE)
    public ResponseEntity getVoucherByCode(@RequestParam("code") String code){
        return new ResponseEntity(voucherService.getVoucherByCode(code), HttpStatus.OK);
    }
    @PostMapping(VoucherConst.API_VOUCHER_CREATE)
    public ResponseEntity<Voucher> save(@Valid @RequestBody Voucher vou){
        if(voucherService.exitsByCode(vou.getCode())){
            throw new AppException(VoucherConst.MSG_ERROR_CODE_DUPLICATE);
        }
        return new ResponseEntity<>(voucherService.saveVoucher(vou),HttpStatus.CREATED);
    }
    @PostMapping(VoucherConst.API_VOUCHER_UPDATE)
    public ResponseEntity<Voucher> update(@Valid @RequestBody Voucher vou ,
                                          @PathVariable("id") Optional<Long> id) throws AppException {
        return new ResponseEntity<>(voucherService.update(vou, id.get()),HttpStatus.OK);
    }
    @GetMapping(VoucherConst.API_VOUCHER_REMOVE)
    public ResponseEntity<String> remove(@PathVariable("id") Optional<Long> id) throws AppException{
        voucherService.delete(id.orElse(null));
        return new ResponseEntity<>("Succesfully!" , HttpStatus.OK);
    }
}
