//package com.poly.datn.be.config;
//
//import com.poly.datn.be.service.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    AccountService accountService;
//
////    @Bean
////    public JwtAuthenticationFilter jwtAuthenticationFilter() {
////        return new JwtAuthenticationFilter();
////    }
//
////    @Bean(BeanIds.AUTHENTICATION_MANAGER)
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        // Get AuthenticationManager bean
////        return super.authenticationManagerBean();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
//        return new BCryptPasswordEncoder();
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(accountService)
////                .passwordEncoder(passwordEncoder());
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();// Ngăn chặn request từ một domain khác
//        http.authorizeRequests().anyRequest().permitAll();
////                .antMatchers("/api/v1/login",
////                        "/api/v1/register",
////                        "/api/v1/products",
////                        "/api/v1/product/page-count",
////                        "/api/v1/account/{username}",
////                        "/api/v1/role/{username}").permitAll();
//        // Cho phép tất cả mọi người truy cập vào địa chỉ này
////                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
////
////        // Thêm một lớp Filter kiểm tra jwt
////        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
