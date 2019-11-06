/*
package com.cus.security.core.validator.filter;

import com.cus.security.core.properties.SecurityProperties;
import com.cus.security.core.validator.ValidatorCodeException;
import com.cus.security.core.validator.ValidatorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SmsValidatorCodeFilter extends OncePerRequestFilter implements InitializingBean {

    */
/**
     * 验证码校验失败处理器
     *//*

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy =  new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<String>();

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    */
/**
     *
     * 设置需要使用验证的url
     * @throws ServletException
     *//*

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String urlProp = securityProperties.getCode().getImage().getUrl();
        if(StringUtils.isNotEmpty(urlProp)) {
            String[] confidUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(),",");
            for(String url:confidUrls) {
                urls.add(url);
            }
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;

        for(String url : urls) {
            if(pathMatcher.match(url,request.getServletPath())) {
                action = true;
                break;
            }
        }

        if(action) {
            try {

                validate(new ServletWebRequest(request));

            } catch (ValidatorCodeException e) {

                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ValidatorCode codeInSession = (ValidatorCode)sessionStrategy.getAttribute(request, ValidatorCodeController.SESSION_KEY_SMS);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidatorCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidatorCodeException("验证码不存在");
        }

        if(codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request,ValidatorCodeController.SESSION_KEY_SMS);
            throw new ValidatorCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)) {
            throw new ValidatorCodeException("验证码不匹配");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}*/
