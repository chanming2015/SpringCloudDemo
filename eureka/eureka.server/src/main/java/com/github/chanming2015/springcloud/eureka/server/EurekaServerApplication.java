package com.github.chanming2015.springcloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * Description:
 * Create Date:2018年6月1日
 * @author XuMaoSen
 * Version:1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
