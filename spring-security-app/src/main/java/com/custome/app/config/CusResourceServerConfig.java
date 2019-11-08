package com.custome.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * ResourceServerConfig比SecurityConfiguration先执行，并且配置优先级更高
 */
@Configuration
@EnableResourceServer
public class CusResourceServerConfig extends ResourceServerConfigurerAdapter  {

    private static final Logger logger = LoggerFactory.getLogger(CusResourceServerConfig.class);

//    public static final String RESOURCE_ID = "resourceserver";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
//        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        logger.info("CusResourceServerConfig中配置HttpSecurity对象执行");
        //只有/user/resource,/user/me端口作为资源服务器的资源
        http.requestMatchers().antMatchers("/user/resource","/user/me")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated();
    }
}
