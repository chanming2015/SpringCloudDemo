package com.github.chanming2015.springcloud.eureka.gateway.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.MediaType;

import io.netty.buffer.UnpooledByteBufAllocator;

public class LogUtils
{
    public static final List<MediaType> legalLogMediaTypes = Arrays.asList(MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_XML);

    public static <T extends DataBuffer> T loggingRequest(Logger log, T buffer)
    {
        return logging(log, ">>>>>>>>>>", buffer);
    }

    public static <T extends DataBuffer> T loggingResponse(Logger log, T buffer)
    {
        return logging(log, "<<<<<<<<<<", buffer);
    }

    @SuppressWarnings("unchecked")
    private static <T extends DataBuffer> T logging(Logger log, String inOrOut, T buffer)
    {
        try
        {
            InputStream dataBuffer = buffer.asInputStream();
            byte[] bytes = toByteArray(dataBuffer);
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            if (log.isDebugEnabled())
            {
                log.debug("\n" + "{}Payload    : {}", inOrOut, new String(bytes));
            }
            DataBufferUtils.release(buffer);
            return (T) nettyDataBufferFactory.wrap(bytes);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static byte[] toByteArray(InputStream input) throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer)))
        {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
