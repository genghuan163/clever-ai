package com.jjh.cleverai.common;
import com.jjh.cleverai.common.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;


@Data
public class Result<T> implements Serializable {



    /**响应码**/
    private Integer code;

    /**返回消息**/
    private String message;

    /**返回数据**/
    private T data;

    private Result(){}

    public static <T> Result<T> ok(T data) {
        Result<T> response = new Result<>();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getResultMessage());
        response.setData(data);
        return response;
    }

    public static <T> Result<T> exception(Integer errCode, String errMessage){
        Result<T> response = new Result<>();
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static <T> Result<T> exception(ResponseEnum responseEnum){
        Result<T> response = new Result<>();
        response.setCode(responseEnum.getCode());
        response.setMessage(responseEnum.getResultMessage());
        return response;
    }
}
