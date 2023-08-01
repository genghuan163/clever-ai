package com.jjh.cleverai.controller;


import com.jjh.cleverai.common.Result;
import com.jjh.cleverai.service.TSlideShowService;
import com.jjh.cleverai.vo.SlideShowVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "home")
public class TSlideShowController {


    @Resource
    private TSlideShowService slideShowService;


    @GetMapping("getSideShowList")
    public Result<List<SlideShowVo>> getSideShowList() {
        return slideShowService.getSideShowList();
    }

}
