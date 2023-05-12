package com.jjh.cleverai.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jjh.cleverai.cache.TokenCache;
import com.jjh.cleverai.common.ORuntimeException;
import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.common.enums.LoginTypeEnum;
import com.jjh.cleverai.common.enums.ResponseEnum;
import com.jjh.cleverai.dao.TUsersMapper;
import com.jjh.cleverai.dto.UserLoginDTO;
import com.jjh.cleverai.dto.UserRegisterDTO;
import com.jjh.cleverai.model.TUsers;
import com.jjh.cleverai.utils.AESDecryptUtils;
import com.jjh.cleverai.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private TokenCache tokenCache;


    @Resource
    private TUsersMapper usersMapper;

    @Override
    public Result<String> userLogin(UserLoginDTO userLoginDTO) {
        LoginTypeEnum loginTypeEnum = userLoginDTO.getLoginTypeEnum();
        TUsers cacheUser = new TUsers();
        if (loginTypeEnum.name().equals(LoginTypeEnum.PHONE.name())) {
            String phoneNumber = userLoginDTO.getPhoneNumber();
            //TODO 发短信到阿里云
        } else {
            TUsers tUsers = usersMapper.selectUserByName(userLoginDTO.getUsername());
            Optional.ofNullable(tUsers).orElseThrow(() -> new ORuntimeException(ResponseEnum.USER_EX));
            String password = userLoginDTO.getPassword();
            if (!AESDecryptUtils.encrypt(password).equals(tUsers.getPassword())) {
                throw new ORuntimeException(ResponseEnum.USER_PASSWORD_FAIL);
            }
            BeanUtils.copyProperties(tUsers, cacheUser);
        }
        String token = JwtUtil.createToken(cacheUser);
        tokenCache.setCache(token, cacheUser);
        return Result.ok(token);
    }

    @Override
    public Result<String> register(UserRegisterDTO userRegisterDTO) {
        if (null != usersMapper.selectUserByName(userRegisterDTO.getUsername())) {
            throw new ORuntimeException(ResponseEnum.USERNAME_EXIST);
        }
        if (null != usersMapper.selectUserByPhone(userRegisterDTO.getPhoneNumber())) {
            throw new ORuntimeException(ResponseEnum.PHONE_EXIST);
        }

        TUsers tUsers = new TUsers();
        BeanUtils.copyProperties(userRegisterDTO, tUsers);
        String password = tUsers.getPassword();
        tUsers.setPassword(AESDecryptUtils.encrypt(password));
        if (usersMapper.insert(tUsers) > 0) {
            return Result.ok("注册成功");
        }
        return Result.exception(ResponseEnum.SYSTEM_FAIL);
    }
}
