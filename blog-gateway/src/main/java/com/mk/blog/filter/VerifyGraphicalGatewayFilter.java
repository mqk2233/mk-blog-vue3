package com.mk.blog.filter;


import cn.hutool.log.StaticLog;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author MK
 * @date 2021/9/8
 */
@Component
public class VerifyGraphicalGatewayFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    /**
     * 登录url
     */
    private static final String LOGIN_URL = "/admin/auth/login";

    @Override
    public GatewayFilter apply(NameConfig config) {
        return (exchange, chain) -> {
            System.out.println("===========================");
            StaticLog.info(exchange.getRequest().getPath().value());
            StaticLog.info(exchange.getRequest().getQueryParams().toSingleValueMap().toString());
            return chain.filter(exchange);
        };
    }

}
