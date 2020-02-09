package com.cus.security.core.authorize.standard;

import com.cus.security.core.authorize.AuthorizeConfigProvider;
import com.cus.security.core.properties.SecurityConstants;
import com.cus.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSignOutUrl()).permitAll();
    }

}
