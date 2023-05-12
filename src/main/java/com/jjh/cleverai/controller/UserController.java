package com.jjh.cleverai.controller;

import com.jjh.cleverai.annotation.PassToken;
import com.jjh.cleverai.common.ORuntimeException;
import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.common.enums.LoginTypeEnum;
import com.jjh.cleverai.common.enums.ResponseEnum;
import com.jjh.cleverai.dto.UserLoginDTO;
import com.jjh.cleverai.dto.UserRegisterDTO;
import com.jjh.cleverai.service.IUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping(value = "user")
public class UserController {


    @Resource
    private IUserService userService;

    @PassToken(filter = true)
    @PostMapping(value = "login")
    public Result<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        Optional.ofNullable(LoginTypeEnum.verify(userLoginDTO.getLoginTypeEnum().name()))
                .orElseThrow(() -> new ORuntimeException(ResponseEnum.PARAM_PASSING_FAIL));

        return userService.userLogin(userLoginDTO);
    }

//    @ConvertParam(value = UserRegisterDTO.class,fieldName = {"phone"})
    @PassToken(filter = true)
    @PostMapping(value = "register")
    public Result<String> register(@RequestBody @Validated UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }
}
