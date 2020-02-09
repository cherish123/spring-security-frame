package com.cus.security.core.social.qq.config;

import com.cus.security.core.properties.SecurityProperties;
import com.cus.security.core.properties.social.QQProperties;
import com.cus.security.core.social.configUtils.SocialAutoConfigurerAdapter;
import com.cus.security.core.social.qq.connect.QQOAuth2ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "cus.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQOAuth2ConnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
    }

//    @Bean({"connect/callback.doConnect", "connect/callback.doConnected"})
//    @ConditionalOnMissingBean(name = "qqConnectedView")
//    public View qqConnectedView() {
//        return new ChenConnectView();
//    }
}
