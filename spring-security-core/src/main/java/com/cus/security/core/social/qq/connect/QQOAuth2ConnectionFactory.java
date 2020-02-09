package com.cus.security.core.social.qq.connect;

import com.cus.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQOAuth2ConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQOAuth2ConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQApiAdapter());
    }
}
