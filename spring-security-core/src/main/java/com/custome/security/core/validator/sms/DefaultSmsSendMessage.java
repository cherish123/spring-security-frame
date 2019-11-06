package com.custome.security.core.validator.sms;

/**
 * cecilia
 * 默认使用的短信服务商
 */
public class DefaultSmsSendMessage implements SmsSendMessage {

    @Override
    public void send(String mobile, String validateCode) {
        System.out.println("向手机号码："+mobile+"用户发送验证码："+validateCode);
    }
}
