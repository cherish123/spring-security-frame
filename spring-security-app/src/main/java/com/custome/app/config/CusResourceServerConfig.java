package com.custome.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableResourceServer
public class CusResourceServerConfig  {

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        //不拦截 oauth 开放的资源
//        http.csrf().disable();
//
//        http//.requestMatchers()//使HttpSecurity接收以"/login/","/oauth/"开头请求。
////                .antMatchers("/oauth/**", "/login/**", "/logout/**")
////                .and()
//                .formLogin()
//                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
//                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
//                .successHandler(cusAuthenticationSuccessHandler)
//                .failureHandler(cusAuthenticationFailureHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**",
//                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll()
//                .anyRequest()
//                .authenticated();
//    }
}
