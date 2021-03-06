package com.github.chanming2015.springcloud.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Create Date:2018年6月4日
 * @author XuMaoSen
 * Version:1.0.0
 */
@FeignClient(value = "service-hi", fallbackFactory = MyFallbackFactory.class)
public interface SchedualServiceHi
{
    @RequestMapping(value = "/hi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<String> sayHiFromClientOne(@RequestParam(value = "name") String name);
}
