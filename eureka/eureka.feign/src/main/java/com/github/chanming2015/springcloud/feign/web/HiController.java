package com.github.chanming2015.springcloud.feign.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.chanming2015.springcloud.feign.service.SchedualServiceHi;

/**
 * Description:
 * Create Date:2018年6月4日
 * @author XuMaoSen
 * Version:1.0.0
 */
@RestController
public class HiController
{
    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sayHi(@RequestParam String name, @RequestHeader(required = true) String cookie)
    {
        return schedualServiceHi.sayHiFromClientOne(name, cookie);
    }
}
