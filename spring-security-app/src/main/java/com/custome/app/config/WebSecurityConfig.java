package com.custome.app.config;

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

//@Order(10)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired()@Qualifier("pwdUserDetailsService")
    private UserDetailsService pwdDetailsService;
//
//    @Autowired
//    private AuthenticationSuccessHandler cusAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler cusAuthenticationFailureHandler;
//
//
//    @Override
//    public void init(WebSecurity web) throws Exception {
//        super.init(web);
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    /**
     * 用来配置拦截保护的请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //不拦截 oauth 开放的资源
        http.csrf().disable();
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**","/login/**","/logout/**").permitAll()
                .anyRequest().authenticated(); //新增login form支持用户登录及授权
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pwdDetailsService);
//        auth.parentAuthenticationManager(authenticationManagerBean());
    }
}
