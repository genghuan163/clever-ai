package com.jjh.cleverai.service;


import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.dto.UserLoginDTO;
import com.jjh.cleverai.dto.UserRegisterDTO;

public interface IUserService {

    Result<String> userLogin(UserLoginDTO userLoginDTO);


    Result<String> register(UserRegisterDTO userRegisterDTO);
}
