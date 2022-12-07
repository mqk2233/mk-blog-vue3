package com.mk.blog.controller;

import com.mk.blog.service.ImgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021/6/22
 */
@RestController
@RequestMapping("img")
public class ImgController {

    @Resource
    private ImgService imgService;

    @GetMapping("get")
    public String getImg(){
        return imgService.getImg();
    }
}
