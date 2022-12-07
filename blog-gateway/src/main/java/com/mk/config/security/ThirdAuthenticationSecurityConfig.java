package com.mk.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class ThirdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private GithubAuthenticationProvider githubAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ThirdAuthenticationFilter filter = new ThirdAuthenticationFilter();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        filter.setAuthenticationManager(authenticationManager);
        //github验证
        http.authenticationProvider(githubAuthenticationProvider);
        super.configure(http);
    }
}
