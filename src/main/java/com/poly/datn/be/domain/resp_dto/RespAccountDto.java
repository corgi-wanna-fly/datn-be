package com.poly.datn.be.domain.resp_dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class RespAccountDto {
    //11 truong
    //account
    private String username;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private Boolean isActive;
    //role
    private String roleName;
    //account detail
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Date birthDate;
}
