package com.poly.datn.be.scheduledtasks;

import com.poly.datn.be.domain.constant.AppConst;
import com.poly.datn.be.entity.Voucher;
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
}
