package com.mk.config;

import com.alibaba.fastjson.JSONObject;
import com.mk.blog.http.ResponseEntity;
import io.netty.util.CharsetUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * @author MK
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String USER_NOT_EXISTS = "用户不存在";

    private static final String USERNAME_PASSWORD_ERROR = "用户密码错误";

    private static final String USER_LOCKED = "用户锁定";

    private static final String[] AUTH_WHITELIST = new String[]{"/admin/auth/login", "/admin/auth/logout"};

    @Resource
    private ReactiveUserDetailsService reactiveUserDetailsService;

    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(new AbstractUserDetailsReactiveAuthenticationManager() {
            @Override
            protected Mono<UserDetails> retrieveUser(String username) {
                return reactiveUserDetailsService.findByUsername(username);
            }
        });
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.formLogin()
                .loginPage("login")
                .authenticationManager(reactiveAuthenticationManager())
                // 登录成功handler
                .authenticationSuccessHandler(authenticationSuccessHandler())
                // 登陆失败handler
                .authenticationFailureHandler(authenticationFailureHandler())
                // 无访问权限handler
                .authenticationEntryPoint(authenticationEntryPoint()).and().logout()
                // 登出成功handler
                .logoutSuccessHandler(serverLogoutSuccessHandler())
                .and().csrf().disable().httpBasic().disable().authorizeExchange()
                // 白名单放行
                .pathMatchers(AUTH_WHITELIST).permitAll().and()
                // 登出处理
                .logout().logoutUrl("/logout").logoutHandler((webFilterExchange, authentication) -> null).and()
                .build();
    }

    /**
     * BCrypt密码编码
     *
     * @return {@link BCryptPasswordEncoder }
     * @author MK
     * @date 2021/11/17 14:59
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录成功handler
     *
     * @return {@link ServerAuthenticationSuccessHandler }
     * @author MK
     * @date 2021/11/17 14:59
     */
    @Bean
    ServerAuthenticationSuccessHandler authenticationSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            String body = JSONObject.toJSONString(ResponseEntity.buildSuccess());
            DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(CharsetUtil.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }

    /**
     * 登陆失败handler
     *
     * @return {@link ServerAuthenticationFailureHandler }
     * @author MK
     * @date 2021/11/17 14:59
     */
    @Bean
    ServerAuthenticationFailureHandler authenticationFailureHandler() {
        return (webFilterExchange, exception) -> {
            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            if (exception instanceof UsernameNotFoundException) {
                return writeErrorMessage(response, USER_NOT_EXISTS);
            } else if (exception instanceof BadCredentialsException) {
                return writeErrorMessage(response, USERNAME_PASSWORD_ERROR);
            } else if (exception instanceof LockedException) {
                return writeErrorMessage(response, USER_LOCKED);
            }
            return writeErrorMessage(response, exception.getMessage());
        };
    }

    /**
     * 无访问权限handler
     *
     * @return {@link ServerAuthenticationEntryPoint }
     * @author MK
     * @date 2021/11/17 14:59
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (serverWebExchange, exception) -> {
            ServerHttpResponse response = serverWebExchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            String body = JSONObject.toJSONString(ResponseEntity.buildByMsg(exception.getMessage(), HttpStatus.UNAUTHORIZED.value()));
            DataBuffer wrap = serverWebExchange.getResponse().bufferFactory().wrap(body.getBytes(CharsetUtil.UTF_8));
            return serverWebExchange.getResponse().writeWith(Flux.just(wrap));
        };
    }

    /**
     * 登出成功handler
     *
     * @return {@link ServerLogoutSuccessHandler }
     * @author MK
     * @date 2021/11/17 14:59
     */
    @Bean
    ServerLogoutSuccessHandler serverLogoutSuccessHandler() {
        return (webFilterExchange, authentication) -> {
            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            String result = JSONObject.toJSONString(ResponseEntity.buildSuccess());
            DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(CharsetUtil.UTF_8));
            return response.writeWith(Mono.just(buffer));
        };
    }

    /**
     * 编写响应消息
     *
     * @param response ServerHttpResponse
     * @param message  响应消息
     * @return {@link Mono<Void> }
     * @author MK
     * @date 2021/11/17 15:00
     */
    private Mono<Void> writeErrorMessage(ServerHttpResponse response, String message) {
        String result = JSONObject.toJSONString(ResponseEntity.buildByMsg(message, 403));
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(CharsetUtil.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}