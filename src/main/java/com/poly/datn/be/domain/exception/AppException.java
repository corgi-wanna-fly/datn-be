package com.poly.datn.be.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppException extends RuntimeException{
    private String bean;
    private String field;
    private String message;
    public String showDetail(){
      return  String.format("Error - Entity: %s - Field: %s - %s", bean, field, message);
    }
}
