package com.poly.datn.be.domain.req_dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReqUpdateAccountDto {
    //12 truong
    //account
    private Long id;
    private String username;
    private String password;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private Boolean isActive;
    //role
    private Long roleId;
    //account detail
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Date birthDate;
}
