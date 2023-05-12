package com.jjh.cleverai.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginTypeEnum {

    PHONE(1, "手机号登录"),

    PASSWORD(2,"账号密码登录");

    private final Integer code;

    private final String resultMessage;

    public static LoginTypeEnum verify(String name){
        for (LoginTypeEnum responseEnum : LoginTypeEnum.values()) {
            if (responseEnum.name().equals(name)){
                return responseEnum;
            }
        }
        return null;
    }

}
