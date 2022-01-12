package com.github.chanming2015.springcloud.eureka.gateway.http;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.StringUtils;

import com.github.chanming2015.springcloud.eureka.gateway.util.LogUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PartnerServerHttpRequestDecorator extends ServerHttpRequestDecorator
{
    private static final Logger log = LoggerFactory.getLogger(PartnerServerHttpRequestDecorator.class);
    private Flux<DataBuffer> body;

    PartnerServerHttpRequestDecorator(ServerHttpRequest delegate, Executor executor)
    {
        super(delegate);
        final String path = delegate.getURI().getPath();
        final String query = delegate.getURI().getQuery();
        final String method = Optional.ofNullable(delegate.getMethod()).orElse(HttpMethod.GET).name();
        final String headers = delegate.getHeaders().entrySet().stream().map(entry -> "            " + entry.getKey() + ": [" + String.join(";", entry.getValue()) + "]").collect(Collectors.joining("\n"));
        final MediaType contentType = delegate.getHeaders().getContentType();
        if (log.isDebugEnabled())
        {
            log.debug("\n" + "HttpMethod : {}\n" + "Uri        : {}\n" + "Headers    : \n" + "{}", method, path + (StringUtils.isEmpty(query) ? "" : "?" + query), headers);
        }
        Flux<DataBuffer> flux = super.getBody();
        if (LogUtils.legalLogMediaTypes.contains(contentType))
        {
            body = flux.publishOn(Schedulers.fromExecutor(executor)).map(dataBuffer -> LogUtils.loggingRequest(log, dataBuffer));
        }
        else
        {
            body = flux;
        }
    }

    @Override
    public Flux<DataBuffer> getBody()
    {
        return body;
    }
}
