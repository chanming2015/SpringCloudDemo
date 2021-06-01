package com.github.chanming2015.springcloud.eureka.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.github.chanming2015.microcloud.security.util.SecurityUtil;

@EnableAuthorizationServer
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * 密码加密工具
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return SecurityUtil.PASSWORD_ENCODER;
    }

    /** @author XuMaoSen
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http.csrf().disable();
    }
}
