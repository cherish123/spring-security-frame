package com.cus.security.core.properties.social;

import com.cus.security.core.social.configUtils.SocialProperties;

public class QQProperties extends SocialProperties {
    /**
     * 服务提供商的默认标志是qq
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
