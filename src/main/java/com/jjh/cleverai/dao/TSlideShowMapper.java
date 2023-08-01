package com.jjh.cleverai.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jjh.cleverai.model.TSlideShow;

import java.util.List;

public interface TSlideShowMapper extends BaseMapper<TSlideShow> {


    List<TSlideShow> getSlideShowList();

}