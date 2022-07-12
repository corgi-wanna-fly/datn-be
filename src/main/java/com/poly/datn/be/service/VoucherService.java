package com.poly.datn.be.service;

import com.poly.datn.be.entity.Voucher;

public interface VoucherService {
    Voucher getVoucherByCode(String code);
    Voucher saveVoucher(Voucher voucher);

    Voucher update(Voucher voucher, Long id);

    void delete(Long id);

    boolean exitsByCode(String code);
}
