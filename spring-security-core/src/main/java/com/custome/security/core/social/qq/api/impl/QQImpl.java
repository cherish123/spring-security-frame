package com.custome.security.core.social.qq.api.impl;

import com.custome.security.core.social.qq.api.QQ;
import com.custome.security.core.social.qq.api.QQUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(getClass());

    private String appId;

    private String openId;

    /**
     * 获取openId的请求
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     *获取QQ服务商提供的用户信息
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * 设置URL_GET_USERINFO所需的三个参数accessToken,oauth_consumer_key,openid
     * accessToken在父类AbstractOAuth2ApiBinding中已经声明
     * @param accessToken
     * @param appId
     */
    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info(result);//callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String apiUrl = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(apiUrl, String.class);

        System.out.println(result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
