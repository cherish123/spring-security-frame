package com.cus.config;

import com.custome.security.core.authentication.AbstractChannelSecurityConfig;
import com.custome.security.core.properties.SecurityConstants;
import com.custome.security.core.properties.SecurityProperties;
import com.custome.security.core.validator.ValidatorCodeSecurityConfig;
import com.custome.security.core.validator.config.SmsValidatorSecurityConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author cecilia
 *
 */
@EnableWebSecurity
public class BroswerSecurityConfigure extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private DataSource dataSource;



    @Autowired()@Qualifier("pwdUserDetailsService")
    private UserDetailsService pwdDetailsService;


    @Autowired
    private SmsValidatorSecurityConfigure smsValidatorSecurityConfigure;

    @Autowired
    private ValidatorCodeSecurityConfig validatorCodeSecurityConfig;

    /**
     * 将第三方登录配置进来
     */
    @Autowired
    private SpringSocialConfigurer cusSocialConfigurer;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
////        tokenRepository.setDataSource(druidDataSource);
////        tokenRepository.setCreateTableOnStartup(true);
//        return tokenRepository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validatorCodeSecurityConfig)
                .and()
            .apply(smsValidatorSecurityConfigure)
                .and()
            .apply(cusSocialConfigurer)
                .and()
//            .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(securityProperties.getBrowser().getTokenExpire())
//                .userDetailsService(pwdUserDetailService)
//                .and()
            .logout()
                .logoutUrl("/signOut") //  spring security默认处理的是/logout
//                .logoutSuccessUrl(securityProperties.getBrowser().getSignOutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pwdDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico");
    }
}
