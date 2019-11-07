package com.custome.app.config;


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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
//
    @Autowired()@Qualifier("pwdUserDetailsService")
    private UserDetailsService pwdDetailsService;

    @Autowired
    private TokenStore tokenStore;

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
                .refreshTokenValiditySeconds(7200)
                .accessTokenValiditySeconds(3600)
                .authorizedGrantTypes("password", "refresh_token","authorization_code")
                .scopes("app","all","read","write");
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(pwdDetailsService);
//                .accessTokenConverter(accessTokenConverter())
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST); //支持GET  POST  请求获取token;
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//                String userName = authentication.getUserAuthentication().getName();
//                final Map<String, Object> additionalInformation = new HashMap<String, Object>();
//                additionalInformation.put("user_name", userName);
//                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//                OAuth2AccessToken token = super.enhance(accessToken, authentication);
//                return token;
//            }
//        };
//        return converter;
//    }
}
