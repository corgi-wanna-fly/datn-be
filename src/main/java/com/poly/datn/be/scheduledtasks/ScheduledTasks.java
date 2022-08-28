package com.poly.datn.be.scheduledtasks;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.entity.Product;
import com.poly.datn.be.entity.Voucher;
import com.poly.datn.be.service.NotificationService;
import com.poly.datn.be.service.ProductService;
import com.poly.datn.be.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {
    @Autowired
    VoucherService voucherService;

    @Autowired
    ProductService productService;

    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "23 59 59 * * ?")
    public void scanVoucher(){
        List<Voucher> vouchers = voucherService.findAll();
        for(Voucher v: vouchers){
            if(v.getExpireDate().isBefore(LocalDate.now())){
                v.setIsActive(AppConst.CONST_IN_ACTIVE);
                voucherService.saveVoucher(v);
            }
        }
    }

    @Scheduled(cron = "23 59 59 * * ?")
    public void scanProduct(){
        List<Voucher> vouchers = voucherService.findAll();
        for(Voucher v: vouchers){
            if(v.getExpireDate().isBefore(LocalDate.now())){
                v.setIsActive(AppConst.CONST_IN_ACTIVE);
                voucherService.saveVoucher(v);
            }
        }
    }
}
