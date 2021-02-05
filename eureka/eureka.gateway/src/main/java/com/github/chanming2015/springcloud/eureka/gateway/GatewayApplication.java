package com.github.chanming2015.springcloud.eureka.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.github.chanming2015.springcloud.eureka.gateway.handler.MyAccessDecisionManager;

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
}
