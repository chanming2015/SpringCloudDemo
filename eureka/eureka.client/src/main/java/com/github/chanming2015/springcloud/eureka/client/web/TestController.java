package com.github.chanming2015.springcloud.eureka.client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Create Date:2018年6月1日
 * @author XuMaoSen
 * Version:1.0.0
 */
@RestController
public class TestController
{
    @Value("${server.port}")
    private String port;
    @RequestMapping("/hi")
    public String home(@RequestParam String name)
    {
        return "hi " + name + ",i am from port:" + port;
    }
}
