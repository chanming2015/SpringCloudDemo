package com.github.chanming2015.springcloud.feign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.github.chanming2015.springcloud.feign.service.impl.SchedualServiceHiHystric;
import com.netflix.hystrix.exception.HystrixTimeoutException;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

/**
 * Description:
 * Create Date:2019年4月11日
 * @author XuMaoSen
 */
@Component
public class MyFallbackFactory implements FallbackFactory<SchedualServiceHi>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyFallbackFactory.class);
    @Autowired
    private SchedualServiceHiHystric feignClient;

    @Override
    public SchedualServiceHi create(Throwable cause)
    {
        LOGGER.error("SchedualServiceHiHystric", cause);
        if (cause instanceof HystrixTimeoutException)
        {
            feignClient.setResponseEntity(null);
        }
        else if (cause instanceof FeignException)
        {
            FeignException e = (FeignException) cause;
            feignClient.setResponseEntity(new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.status())));
        }
        return feignClient;
    }
}
