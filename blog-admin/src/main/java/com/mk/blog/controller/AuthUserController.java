package com.mk.blog.controller;

import com.mk.blog.http.ResponseEntity;
import com.mk.blog.service.IAuthUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021/7/29
 */
@RestController
@RequestMapping("auth")
public class AuthUserController {

    @Resource
    private IAuthUserService authUserService;

    @GetMapping("get-user-info/{userName}")
    public ResponseEntity<?> getUserInfo(@PathVariable String userName) {
        return ResponseEntity.buildData(authUserService.getUserByName(userName));
    }

}
