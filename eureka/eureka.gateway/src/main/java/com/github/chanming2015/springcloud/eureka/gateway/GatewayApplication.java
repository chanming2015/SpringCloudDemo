package com.github.chanming2015.springcloud.eureka.gateway;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

/**
 * Description: 服务网关 <br/> 
 * Create Date:2021年1月14日 <br/> 
 * @author XuMaoSen
 */
@SpringBootApplication(scanBasePackages = {"com.github.chanming2015.utils", "com.github.chanming2015.microcloud.security"})
@EntityScan("com.github.chanming2015.microcloud.security.entity")
@EnableJpaRepositories("com.github.chanming2015.microcloud.security.repository")
public class GatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Value("${security.oauth2.authorization.jwt.key-store}")
    private Resource keyStore;
    @Value("${security.oauth2.authorization.jwt.key-store-password}")
    private String skp;
    @Value("${security.oauth2.authorization.jwt.key-alias}")
    private String keyAlias;
    @Value("${security.oauth2.authorization.jwt.key-password}")
    private String kp;

    @Bean
    public KeyPair keyPair()
    {
        char[] keyStorePassword = skp.toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);
        char[] keyPassword = kp.toCharArray();
        return keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword);
    }
}
