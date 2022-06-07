package com.poly.datn.be.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(AppException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", new Date());
        body.put("Status", HttpStatus.BAD_REQUEST);
        body.put("Errors", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
