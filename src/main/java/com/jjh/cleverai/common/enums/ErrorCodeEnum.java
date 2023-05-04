package com.jjh.cleverai.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    //公共模块
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(401, "未授权"),
    ILLEGAL_VALUE(404, "参数非法"),
    OPERATION_FAILED(404, "操作失败");

    int value;
    String message;
}