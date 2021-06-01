package com.github.chanming2015.springcloud.eureka.gateway.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

/**
 * Description: 测试接口 <br/> 
 * Create Date:2021年1月28日 <br/> 
 * @author XuMaoSen
 */
@RestController
public class TestController
{
    @GetMapping(value = "/test")
    public ResponseEntity<JSONObject> getJwk()
    {
        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
