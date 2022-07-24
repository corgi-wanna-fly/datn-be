package com.poly.datn.be.service.impl;

import com.poly.datn.be.domain.constant.SaleConst;
import com.poly.datn.be.domain.exception.AppException;
import com.poly.datn.be.entity.Sale;
import com.poly.datn.be.repo.SaleRepo;
import com.poly.datn.be.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SaleServieImpl implements SaleService {
    @Autowired
    SaleRepo saleRepo;

    @Override
    public Sale getSaleById(Long id) {
        Optional<Sale> optionalSale = saleRepo.findById(id);
        if (!optionalSale.isPresent()) {
            throw new AppException(SaleConst.MSG_ERROR_SALE_NOT_EXIST);
        }
        return optionalSale.get();
    }

    @Override
    public Sale saveSale(Sale sale) {
        sale.setCreateDate(LocalDate.now());
        sale.setModifyDate(LocalDate.now());
        return saleRepo.save(sale);
    }

    @Override
    public Sale updateSale(Sale sale) {
        Optional<Sale> optionalSale = saleRepo.findById(sale.getId());
        if (optionalSale.isPresent()) {
            Sale sl = optionalSale.get();
            sl.setName(sale.getName());
            sl.setDescription(sale.getDescription());
            sl.setCreateDate(sl.getCreateDate());
            sl.setModifyDate(sale.getModifyDate());
            sl.setDiscount(sale.getDiscount());
            saleRepo.save(sl);
            return sl;
        } else {
            throw new AppException(SaleConst.MSG_ERROR_SALE_NOT_EXIST);
        }
    }

    @Override
    public void delete(Long id) {
        if (!saleRepo.existsById(id)) {
            throw new AppException(SaleConst.MSG_ERROR_SALE_NOT_EXIST);
        }
        Sale sale = saleRepo.findById(id).orElse(null);
        sale.setIsActive(false);
        saleRepo.save(sale);

    }

    @Override
    public Page<Sale> getToTalPage(Pageable pageable) {
        return saleRepo.findAll(pageable);
    }

}


