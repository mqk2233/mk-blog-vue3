package com.mk.blog.controller;

import com.mk.blog.api.IAuthService;
import com.mk.blog.dto.AuthUserDto;
import com.mk.blog.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021/12/17
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private IAuthService iAuthService;

    @GetMapping("login-success")
    public ResponseEntity<?> loginSuccess() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        AuthUserDto authUserDto = iAuthService.getUserInfo(username).getData();
        return ResponseEntity.buildData(authUserDto);
    }
}
