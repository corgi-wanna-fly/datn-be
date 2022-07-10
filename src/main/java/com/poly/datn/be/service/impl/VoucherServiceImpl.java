package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.domain.constant.VoucherConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Voucher;
import com.poly.datn.be.repo.VoucherRepo;
import com.poly.datn.be.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepo voucherRepo;

    @Override
    public Voucher getVoucherByCode(String code) {
        Optional<Voucher> optionalVoucher = voucherRepo.findVoucherByCode(code);
        if(optionalVoucher.isPresent()){
            Voucher voucher = optionalVoucher.get();
            if(voucher.getExpireDate().isBefore(LocalDate.now())){
                throw new AppException(VoucherConst.MSG_ERROR_VOUCHER_EXPIRED);
            }
            if(voucher.getCount() == 0){
                throw new AppException(VoucherConst.MSG_ERROR_VOUCHER_USED);
            }
            return voucher;
        }else{
            throw new AppException(VoucherConst.MSG_ERROR_VOUCHER_NOT_EXIST);
        }
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepo.save(voucher);
    }
}
