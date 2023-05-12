package com.jjh.cleverai.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jjh.cleverai.model.TUsers;
import org.apache.ibatis.annotations.Param;

public interface TUsersMapper extends BaseMapper<TUsers> {


    TUsers selectUserByName(@Param(value = "username") String username);


    TUsers selectUserByPhone(@Param(value = "phone") String phone);
}