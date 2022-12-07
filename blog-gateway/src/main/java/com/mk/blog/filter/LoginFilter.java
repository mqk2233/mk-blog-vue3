package com.mk.blog.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.config.TokenUtil;
import com.mk.blog.entity.admin.AuthUser;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author MK
 * @date 2022/3/2
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    private final TokenUtil tokenUtil;
    private final AuthenticationManager authenticationManager;

    public LoginFilter(TokenUtil tokenUtil, AuthenticationManager authenticationManager) {
        this.tokenUtil = tokenUtil;
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        // 设置登录路径和请求方法
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/login", "POST"));
    }


    /**
     * 获取用户名密码
     *
     * @param request
     * @param response
     * @return {@link Authentication }
     * @author MK
     * @date 2022/3/2 22:35
     */
    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws AuthenticationException {
        // 获取用户名密码生成token
        AuthUser user = new ObjectMapper().readValue(request.getInputStream(), AuthUser.class);
        return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), AuthorityUtils.createAuthorityList("admin")));
    }

    /**
     * 认证成功处理
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @author MK
     * @date 2022/3/2 22:36
     */
    @Override
    protected void successfulAuthentication(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthUser user = (AuthUser) authResult.getPrincipal();
        String token = tokenUtil.createToken(user.getUserName());
        //response对象返回token
    }

    /**
     * 认证失败处理
     *
     * @param request
     * @param response
     * @param failed
     * @author MK
     * @date 2022/3/2 22:36
     */
    @Override
    protected void unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // response返回错误提示
    }
}
