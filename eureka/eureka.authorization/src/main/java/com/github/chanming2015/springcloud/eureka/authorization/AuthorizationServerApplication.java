package com.github.chanming2015.springcloud.eureka.authorization;

import java.security.KeyPair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties.Jwt;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

/**
 *
 * Description:
 * Create Date:2018年6月1日
 * @author XuMaoSen
 * Version:1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.github.chanming2015.utils", "com.github.chanming2015.microcloud.security", "com.github.chanming2015.springcloud.eureka.authorization"})
@EntityScan("com.github.chanming2015.microcloud.security.entity")
@EnableJpaRepositories("com.github.chanming2015.microcloud.security.repository")
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class AuthorizationServerApplication
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public KeyPair keyPair(AuthorizationServerProperties properties, ApplicationContext context)
    {
        Jwt jwt = properties.getJwt();
        char[] keyStorePassword = jwt.getKeyStorePassword().toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(context.getResource(jwt.getKeyStore()), keyStorePassword);
        char[] keyPassword = jwt.getKeyPassword().toCharArray();
        return keyStoreKeyFactory.getKeyPair(jwt.getKeyAlias(), keyPassword);
    }
}
