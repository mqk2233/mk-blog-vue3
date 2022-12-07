package com.mk.blog.api;

import com.mk.blog.dto.AuthUserDto;
import com.mk.blog.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MK
 * @date 2021/12/17
 */
@Component
@FeignClient(name = "mk-blog-admin", path = "admin")
public interface IAuthService {

    /**
     * 通过用户名获取用户信息
     *
     * @param userName 用户名
     * @return {@link ResponseEntity<AuthUserDto> }
     * @author MK
     * @date 2021/12/17 11:13
     */
    @GetMapping("/auth/get-user-info/{userName}")
    ResponseEntity<AuthUserDto> getUserInfo(@PathVariable String userName);

}
