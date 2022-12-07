package com.mk.blog.common;

import com.mk.blog.api.IAuthService;
import com.mk.blog.dto.AuthUserDto;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021/12/17
 */
@Component
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    @Resource
    private IAuthService iAuthService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        AuthUserDto authUserDto = iAuthService.getUserInfo(username).getData();
        return Mono.just(new org.springframework.security.core.userdetails.User(
                authUserDto.getUserName(),
                authUserDto.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN")));
    }
}
