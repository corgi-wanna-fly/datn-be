package com.poly.datn.be.domain.req_dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReqForgotPasswordDto {
    @NotNull
    @NotEmpty
    private String username;
}
