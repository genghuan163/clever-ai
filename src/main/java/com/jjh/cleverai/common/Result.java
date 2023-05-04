package com.jjh.cleverai.common;
import com.jjh.cleverai.common.enums.ErrorCodeEnum;
import lombok.Data;


@Data
public class Result {


    public static final int SUCCESS = 200;


    private Integer status;


    private String msg;


    private Object data;


    public Result() {

    }

    public Result(Object data) {
        this.status = SUCCESS;
        this.msg = "OK";
        this.data = data;
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok() {
        return new Result(null);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result error(ErrorCodeEnum errorCodeEnum) {
        return new Result(errorCodeEnum.getValue(), errorCodeEnum.getMessage(), "");
    }

    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public static Result build(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

}
