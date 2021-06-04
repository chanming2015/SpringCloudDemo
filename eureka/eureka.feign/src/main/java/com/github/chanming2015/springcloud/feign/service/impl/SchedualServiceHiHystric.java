package com.github.chanming2015.springcloud.feign.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.chanming2015.springcloud.feign.service.SchedualServiceHi;

/**
 * Description:
 * Create Date:2018年6月4日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Service
public class SchedualServiceHiHystric implements SchedualServiceHi
{
    private static final ResponseEntity<String> RESPONSE_ENTITY_TIMEOUT = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    private ResponseEntity<String> responseEntity = null;

    public void setResponseEntity(ResponseEntity<String> responseEntity)
    {
        this.responseEntity = responseEntity == null ? RESPONSE_ENTITY_TIMEOUT : responseEntity;
    }

    @Override
    public ResponseEntity<String> sayHiFromClientOne(String name, String cookie)
    {
        return responseEntity;
    }
}
