package com.mk.blog.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MK
 * @date 2021/10/15
 */
@Component
@FeignClient(name="mk-blog-admin")
public interface IUserService {

    @GetMapping("/admin/auth/login")
    Object getTree();

}
