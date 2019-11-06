package com.custome.security.core.properties;

import com.custome.security.core.properties.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cus.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidatorProperties code = new ValidatorProperties();

    private SocialProperties social = new SocialProperties();

    /**
     * 登录方式选择：默认为用户名密码
     * @return
     */
//    private LoginType loginType = LoginType.USER_PWD;

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidatorProperties getCode() {
        return code;
    }

    public void setCode(ValidatorProperties code) {
        this.code = code;
    }

//    public LoginType getLoginType() {
//        return loginType;
//    }
//
//    public void setLoginType(LoginType loginType) {
//        this.loginType = loginType;
//    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
