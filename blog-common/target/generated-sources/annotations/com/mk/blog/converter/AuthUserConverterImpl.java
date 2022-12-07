package com.mk.blog.converter;

import com.mk.blog.dto.AuthUserDto;
import com.mk.blog.entity.admin.AuthUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-03T23:35:14+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class AuthUserConverterImpl implements AuthUserConverter {

    @Override
    public AuthUserDto toConvertDto(AuthUser source) {
        if ( source == null ) {
            return null;
        }

        AuthUserDto authUserDto = new AuthUserDto();

        authUserDto.setId( source.getId() );
        authUserDto.setUserName( source.getUserName() );
        authUserDto.setPassword( source.getPassword() );
        authUserDto.setIsDisable( source.getIsDisable() );
        authUserDto.setCreateTime( source.getCreateTime() );

        return authUserDto;
    }
}
