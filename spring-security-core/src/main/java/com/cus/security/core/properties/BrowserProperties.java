package com.cus.security.core.properties;

public class BrowserProperties {

    private String loginPage = "/login.html";

    private String signUpUrl = "/demo-signUp.html";

    private String signOutUrl = "/logout.html";

    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    private int tokenExpire = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }

    public int getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(int tokenExpire) {
        this.tokenExpire = tokenExpire;
    }


}
