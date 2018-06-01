package com.github.chanming2015.springcloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * Description:
 * Create Date:2018年6月1日
 * @author XuMaoSen
 * Version:1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
