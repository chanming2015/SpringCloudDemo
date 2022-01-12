package com.github.chanming2015.springcloud.eureka.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

import com.github.chanming2015.springcloud.eureka.gateway.handler.MyAccessDecisionManager;
import com.github.chanming2015.springcloud.eureka.gateway.http.PayloadServerWebExchangeDecorator;
import com.github.chanming2015.springcloud.eureka.gateway.util.MyAsyncTaskExecutor;

/**
 * Description: 服务网关 <br/> 
 * Create Date:2021年1月14日 <br/> 
 * @author XuMaoSen
 */
@SpringBootApplication(scanBasePackages = {"com.github.chanming2015.utils", "com.github.chanming2015.microcloud.security", "com.github.chanming2015.springcloud.eureka.gateway"})
@EntityScan("com.github.chanming2015.microcloud.security.entity")
@EnableJpaRepositories("com.github.chanming2015.microcloud.security.repository")
public class GatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)
    {
        http.oauth2Login().and().authorizeExchange().anyExchange().access(myAccessDecisionManager);
        return http.csrf().disable().build();
    }

    @Autowired
    private MyAsyncTaskExecutor myAsyncTaskExecutor;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter webFilter()
    {
        return (exchange, chain) ->
        {
            final ServerHttpRequest request = exchange.getRequest();
            if (!request.getURI().getPath().contains("swagger") && !request.getURI().getPath().contains("health"))
            {
                return chain.filter(new PayloadServerWebExchangeDecorator(exchange, myAsyncTaskExecutor));
            }
            return chain.filter(exchange);
        };
    }
}
