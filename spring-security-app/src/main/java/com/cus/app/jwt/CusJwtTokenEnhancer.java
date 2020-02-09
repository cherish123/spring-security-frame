package com.cus.app.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CusJwtTokenEnhancer implements TokenEnhancer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> info = new HashMap<>();
        info.put("company","cus");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        logger.info("setAdditionalInformation 成功");

        return accessToken;
    }
}
