package com.poly.datn.be;


import com.poly.datn.be.entity.Account;
import com.poly.datn.be.entity.AppUserRole;
import com.poly.datn.be.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class DatnBeApplication {
//    final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DatnBeApplication.class, args);
    }

//    @Override
//    public void run(String... params) throws Exception {
//        Account admin = new Account();
//        admin.setUsername("admin");
//        admin.setPassword("admin");
//        admin.setCreateDate(java.time.LocalDate.now());
//        admin.setModifyDate(java.time.LocalDate.now());
//        admin.setIsActive(true);
//        admin.setAppUserRoles(new ArrayList<>(Arrays.asList(AppUserRole.ROLE_ADMIN)));
//
////implements CommandLineRunner
//
//        userService.signup(admin);
//        Account client = new Account();
//        client.setUsername("client");
//        client.setPassword("client");
//        admin.setCreateDate(java.time.LocalDate.now());
//        admin.setModifyDate(java.time.LocalDate.now());
//        client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));
//
//        userService.signup(client);
//    }
}

