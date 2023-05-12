package com.jjh.cleverai.common;

import com.jjh.cleverai.common.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ORuntimeException extends RuntimeException {

    private final Integer code;

    public ORuntimeException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    public ORuntimeException(ResponseEnum responseEnum){
        super(responseEnum.getResultMessage());
        this.code = responseEnum.getCode();
    }

    public ORuntimeException(String message){
        super(message);
        this.code = ResponseEnum.SYSTEM_FAIL.getCode();
    }
}
