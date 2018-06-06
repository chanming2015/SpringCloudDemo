package com.github.chanming2015.springcloud.feign.service.impl;

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
    @Override
    public String sayHiFromClientOne(String name)
    {
        return "sorry " + name;
    }
}
