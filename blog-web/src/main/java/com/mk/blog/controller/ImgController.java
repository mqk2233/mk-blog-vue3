package com.mk.blog.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mk.blog.handler.CustomerBlockHandler;
import com.mk.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021/6/23
 */
@RestController
@RequestMapping("img")
@RefreshScope
public class ImgController {

    @Value("${config.info}")
    private String dd;
    @Resource
    private IUserService userService;

    @GetMapping("dd")
    @SentinelResource(value = "dd", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException")
    public String dd() {
        return dd;
    }

    @GetMapping("bb")
    public Object bb(){
        return userService.getTree();
    }
}
