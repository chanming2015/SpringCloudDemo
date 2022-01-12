package com.github.chanming2015.springcloud.eureka.gateway.http;

import java.util.concurrent.Executor;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

public class PayloadServerWebExchangeDecorator extends ServerWebExchangeDecorator
{
    private final ServerHttpRequest requestDecorator;
    private final ServerHttpResponse responseDecorator;

    public PayloadServerWebExchangeDecorator(ServerWebExchange delegate, Executor executor)
    {
        super(delegate);
        requestDecorator = new PartnerServerHttpRequestDecorator(delegate.getRequest(), executor);
        responseDecorator = delegate.getResponse();
    }

    @Override
    public ServerHttpRequest getRequest()
    {
        return requestDecorator;
    }

    @Override
    public ServerHttpResponse getResponse()
    {
        return responseDecorator;
    }
}
