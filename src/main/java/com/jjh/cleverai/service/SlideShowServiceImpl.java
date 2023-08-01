package com.jjh.cleverai.service;


import com.google.common.collect.Lists;
import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.dao.TSlideShowMapper;
import com.jjh.cleverai.model.TSlideShow;
import com.jjh.cleverai.vo.SlideShowVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huan.Geng
 * @date 2023/7/28 17:11
 */
@Service
public class SlideShowServiceImpl implements TSlideShowService {

    @Resource
    private TSlideShowMapper slideShowMapper;

    @Override
    public Result<List<SlideShowVo>> getSideShowList() {
        List<TSlideShow> slideShowList = slideShowMapper.getSlideShowList();
        if (CollectionUtils.isEmpty(slideShowList)) {
            return Result.ok(Lists.newArrayList());
        }

        List<SlideShowVo> slideShowVoList = slideShowList.stream().map(s -> {
            SlideShowVo slideShowVo = new SlideShowVo();
            BeanUtils.copyProperties(s, slideShowVo);
            return slideShowVo;
        }).collect(Collectors.toList());

        return Result.ok(slideShowVoList);
    }
}
