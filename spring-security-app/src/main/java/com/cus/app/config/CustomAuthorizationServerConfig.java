package com.cus.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired()@Qualifier("pwdUserDetailsService")
    private UserDetailsService pwdDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private Environment env;

//
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()             // 使用in-memory存储客户端信息
                .withClient(env.getProperty("security.oauth2.client.client-id"))       // client_id
//                .secret(env.getProperty("security.oauth2.client.client-secret"))   //自定义用户名密码登录方式   successhandle中需要
                .secret(passwordEncoder.encode(env.getProperty("security.oauth2.client.client-secret"))) // 默认拿code换取token这一步需要
                .redirectUris("http://example.com")
                .refreshTokenValiditySeconds(7200) // 默认是30天
                .accessTokenValiditySeconds(3600) // 默认是12h
                .authorizedGrantTypes("password", "refresh_token","authorization_code")
                .scopes("app","all","read","write");
//                .resourceIds("resoureserver") //该client可以访问的资源服务器id
//                .authorities("ROLE_CLIENT") //该client拥有的权限，资源服务器可以依据该处定义的范围对client进行鉴权，密码模式或者授权码模式不需要指定该字段
//                .autoApprove("read");//自动批准的scope在批准页不需要显示，即不需要用户确认批准。只适用于授权码模式
    }

    /**
     * 配置授权服务器的安全，意味着/oauth/token端点和/oauth/authorize端点都应该是安全的。
     * @param endpoints
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }

    /**
     * 用来配置授权服务特性的，主要是一些非安全的特性，比如token存储、token自定义、授权模式
     * 若需要使用密码授权模式，需指定一个AuthenticationManager
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(pwdDetailsService);//需要显示指定userDetailsService，否则refresh_token报错

        if(jwtAccessTokenConverter != null && tokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList<TokenEnhancer>();
            enhancerList.add(tokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancerList);

            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }
}
