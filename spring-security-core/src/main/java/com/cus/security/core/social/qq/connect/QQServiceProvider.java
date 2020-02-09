package com.cus.security.core.social.qq.connect;

import com.cus.security.core.social.qq.api.QQ;
import com.cus.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appId;

    /**
     * 获取授权码的请求地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 拿授权码换token的请求地址
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * 这个方法中的模板OAuthTemplate完成授权码以及token的获取
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
