package com.mk.blog.dao.admin;

import com.mk.blog.entity.admin.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MK
 * @describe 用户表mapper类
 * @date 2021-07-29 20:12:46
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}
