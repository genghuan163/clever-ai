package com.jjh.cleverai.service;


import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.dto.UserLoginDTO;
import com.jjh.cleverai.dto.UserRegisterDTO;
import com.jjh.cleverai.vo.SlideShowVo;

import java.util.List;

public interface TSlideShowService {

    Result<List<SlideShowVo>> getSideShowList();
}
