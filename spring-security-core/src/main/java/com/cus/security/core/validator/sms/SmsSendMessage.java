package com.cus.security.core.validator.sms;

/**
 * cecilia
 * 用于不同短信服务商
 */
public interface SmsSendMessage {

    public void send(String mobile, String validateCode);
}
