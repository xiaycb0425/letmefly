package com.kingstar.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xiayc
 * @date 2020/8/28 10:12
 * @desc 在这里实现jwt的登录验证
 */
@Component
public class JWTTokenFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();


        // 请求url
        String path = request.getURI().getPath();

        // 这里可以做请求放行
        if (path != null) {

        }
        // 这里可以做权限认证


        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
