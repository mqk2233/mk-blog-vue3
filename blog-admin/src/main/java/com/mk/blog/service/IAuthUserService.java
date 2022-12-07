package com.mk.blog.service;

import com.mk.blog.dto.AuthUserDto;

/**
 * @author MK
 * @date 2021/7/29
 */
public interface IAuthUserService {

    /**
     * 方法描述
     *
     * @param userName 通过用户名获取用户信息
     * @return {@link AuthUserDto }
     * @author MK
     * @date 2021/12/17 10:39
     */
    AuthUserDto getUserByName(String userName);
}