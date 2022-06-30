package com.poly.datn.be.domain.dto;

import com.poly.datn.be.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqOrderDto {
    private String fullname;
    private String phone;
    private String address;
    private String note;
    private Double total;
    private String email;
    private Boolean isPending;
    private Long accountId;
    private Collection<OrderDetail> orderDetails;
    private String code;
}
