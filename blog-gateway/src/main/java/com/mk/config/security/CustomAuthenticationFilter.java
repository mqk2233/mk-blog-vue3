//package com.mk.config.security;
//
//import cn.hutool.core.util.StrUtil;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author MK
// * @date 2022/8/3
// */
//public class ThirdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    private static final String authTypeParameter = "authType";
//
//    protected ThirdAuthenticationFilter() {
//        super(new AntPathRequestMatcher("/login/doLogin", "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        if (!request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException(
//                    "Authentication method not supported: " + request.getMethod());
//        }
//        String authType = request.getParameter(authTypeParameter);
//        if (StrUtil.isBlank(authType)) {
//            authType = "default";
//        }
//        String principal = request.getParameter("principal");
//        String credentials = request.getParameter("credentials");
//        AbstractAuthenticationToken authRequest = null;
//        switch (authType) {
//            case "github":
//                authRequest = new GithubAuthenticationToken(principal, credentials);
//                break;
//            case "default":
//                authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
//        }
//        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//}
//}
