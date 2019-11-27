package com.github.chanming2015.springcloud.eureka.client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

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

    @GetMapping(value = "/hi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> home(@RequestParam String name, @RequestParam(required = false) String value)
    {
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("value", value);
        result.put("port", port);
        return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
    }
}
