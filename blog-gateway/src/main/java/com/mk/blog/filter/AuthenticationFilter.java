package com.mk.blog.filter;

import cn.hutool.core.util.StrUtil;
import com.mk.config.TokenUtil;
import com.mk.blog.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author MK
 * @date 2022/3/2
 */
public class AuthenticationFilter extends BasicAuthenticationFilter {

    private final RedisUtils redisUtils;

    private final TokenUtil tokenUtil;

    public AuthenticationFilter(AuthenticationManager authenticationManager, RedisUtils redisUtils, TokenUtil tokenUtil) {
        super(authenticationManager);
        this.tokenUtil = tokenUtil;
        this.redisUtils = redisUtils;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        // 如果有权限，放到权限上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken, tokenUtil, r);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Token");
        String username = null;
        if (StrUtil.isNotBlank(token)) {
            username = tokenUtil.getUserInfo(token);
        }
        List<String> roles = (List<String>) redisUtils.get(username);
        return new UsernamePasswordAuthenticationToken(username, token, AuthorityUtils.createAuthorityList(roles.toArray(new String[0])));
    }
}
