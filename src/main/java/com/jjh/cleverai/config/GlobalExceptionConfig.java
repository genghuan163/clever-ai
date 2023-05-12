package com.jjh.cleverai.config;


import com.jjh.cleverai.common.ORuntimeException;
import com.jjh.cleverai.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfig {
    @ExceptionHandler(ORuntimeException.class)
    public Result<ORuntimeException> handle(ORuntimeException e) {
        log.error(e.getMessage());
        return Result.exception(e.getCode(), e.getMessage());
    }
}