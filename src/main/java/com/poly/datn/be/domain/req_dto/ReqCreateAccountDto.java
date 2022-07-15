package com.poly.datn.be.domain.req_dto;

import lombok.Data;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ReqCreateAccountDto {
    //13 truong
    //account
    private String username;
    private String password;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private Boolean isActive;
    //role
    private Long roleId;
    //account detail
    private Long accountId;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Date birthDate;
}
