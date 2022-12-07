package com.mk.blog.converter;

import com.mk.blog.dto.AuthUserDto;
import com.mk.blog.entity.admin.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author MK
 */
@Mapper
public interface AuthUserConverter {

    AuthUserConverter INSTANCE = Mappers.getMapper(AuthUserConverter.class);

    /**
     * 用户对象转dto对象
     *
     * @param source 用户对象
     * @return {@link AuthUserDto }
     * @author MK
     * @date 2021/12/17 10:36
     */
    AuthUserDto toConvertDto(AuthUser source);
}
