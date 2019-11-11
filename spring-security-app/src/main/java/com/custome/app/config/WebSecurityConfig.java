package com.custome.app.config;

import com.custome.security.core.properties.SecurityConstants;
import com.custome.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired()@Qualifier("pwdUserDetailsService")
    private UserDetailsService pwdDetailsService;
//
    @Autowired
    private AuthenticationSuccessHandler cusAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler cusAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;
//
//
//    @Override
//    public void init(WebSecurity web) throws Exception {
//        super.init(web);
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 用来配置拦截保护的请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("WebSecurityConfig中配置HttpSecurity对象执行");

        //不拦截 oauth 开放的资源
        http.csrf().disable();
        http.formLogin()
//                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
//                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(cusAuthenticationSuccessHandler)
//                .failureHandler(cusAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(//SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        //SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                        "/oauth/**","/login/**","/logout/**").permitAll()
                .anyRequest().authenticated(); //新增login form支持用户登录及授权
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pwdDetailsService);
    }
}
