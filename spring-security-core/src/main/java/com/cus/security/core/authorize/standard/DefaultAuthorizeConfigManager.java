package com.cus.security.core.authorize.standard;

import com.cus.security.core.authorize.AuthorizeConfigManager;
import com.cus.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    /**
     * 将每个authorizeRequests()对象的授权请求整合
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for(AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
//        config.anyRequest().authenticated();
    }
}
