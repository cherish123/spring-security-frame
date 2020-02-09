package com.cus.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;


public interface AuthorizeConfigProvider {

    /**
     *
     * 权限模块，用于各个应用实现自己的authorizeRequests（）授权要求
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
