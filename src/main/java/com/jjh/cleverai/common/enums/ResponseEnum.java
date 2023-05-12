package com.jjh.cleverai.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {

    SUCCESS(200, "操作成功"),

    FAIL(2001,"获取数据失败"),

    NO_TOKEN(4000,"无token，请重新登录"),

    TOKEN_EX(4001,"token验证失败，请重新登录"),

    USER_EX(4002,"用户不存在，请重新登录"),

    USER_PASSWORD_FAIL(4003,"用户密码错误"),

    USERNAME_EXIST(4004,"用户名已存在"),

    PHONE_EXIST(4005,"该手机号已经注册"),

    PERM_VERIFICATION_FAIL(4100,"授权失败"),

    PARAM_PASSING_FAIL(5001,"参数传入错误"),

    ERROR(5000,"错误请求"),

    SYSTEM_FAIL(7000,"系统异常");

    private final Integer code;

    private  final String  resultMessage;


    public static ResponseEnum getResultCode(Integer code){
        for (ResponseEnum value : ResponseEnum.values()) {
            if (code.equals(value.getCode())){
                return value;
            }
        }
        return ResponseEnum.ERROR;
    }

}
