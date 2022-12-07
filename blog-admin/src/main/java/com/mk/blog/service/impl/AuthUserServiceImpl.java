package com.mk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.converter.AuthUserConverter;
import com.mk.blog.dao.admin.AuthUserMapper;
import com.mk.blog.dto.AuthUserDto;
import com.mk.blog.entity.admin.AuthUser;
import com.mk.blog.service.IAuthUserService;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @date 2021/12/17
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

    @Override
    public AuthUserDto getUserByName(String userName) {
        return AuthUserConverter.INSTANCE.toConvertDto(lambdaQuery()
                .eq(AuthUser::getUserName, userName)
                .one());
    }
}
