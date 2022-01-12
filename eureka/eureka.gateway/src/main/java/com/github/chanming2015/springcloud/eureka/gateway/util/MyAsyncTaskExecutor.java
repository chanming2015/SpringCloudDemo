package com.github.chanming2015.springcloud.eureka.gateway.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Create Date:2018年12月19日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Component
public class MyAsyncTaskExecutor extends ThreadPoolTaskExecutor
{
    private static final long serialVersionUID = -3265937031143389760L;
    @Value("${cpu.threads:1}")
    private Integer cpuThreads;

    /**
     * Description:
     * Create Date:2018年12月20日
     * @author XuMaoSen
     */
    @Override
    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler)
    {
        cpuThreads = cpuThreads < 8 ? 8 : cpuThreads;
        int maxPoolSize = cpuThreads * 2;
        setCorePoolSize(cpuThreads);
        setMaxPoolSize(maxPoolSize);
        setQueueCapacity(cpuThreads);
        return super.initializeExecutor(threadFactory, rejectedExecutionHandler);
    }
}
