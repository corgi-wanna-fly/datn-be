package com.poly.datn.be.api;

import com.poly.datn.be.domain.constant.LoginConst;
import com.poly.datn.be.domain.req_dto.ReqLogin;
import com.poly.datn.be.domain.resp_dto.RespLogin;
import com.poly.datn.be.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LoginConst.USER)
@RequiredArgsConstructor
public class LoginApi {
    private final UserService userService;


    @PostMapping(LoginConst.LOGIN)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public RespLogin login(@RequestBody ReqLogin reqLogin) {
        return new RespLogin(userService.signin(reqLogin.getUsername(), reqLogin.getPassword())) ;
    }
}
