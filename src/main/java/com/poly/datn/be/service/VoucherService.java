package com.poly.datn.be.service;

import com.poly.datn.be.entity.Voucher;

public interface VoucherService {
    Voucher getVoucherByCode(String code);
    Voucher saveVoucher(Voucher voucher);
}
