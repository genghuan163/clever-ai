package com.jjh.cleverai.dto;


import com.jjh.cleverai.common.enums.LoginTypeEnum;
import lombok.Data;

@Data
public class UserLoginDTO {

    private String username;

    private String password;

    private String phoneNumber;

    //登录方式 PHONE 手机号登录 PASSWORD 账号密码登录
    private LoginTypeEnum loginTypeEnum = LoginTypeEnum.PASSWORD;
}
