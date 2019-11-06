package com.custome.security.core.validator.sms;


import com.custome.security.core.validator.ValidatorCode;
import com.custome.security.core.properties.SecurityConstants;
import com.custome.security.core.validator.impl.AbStractValidatorCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidatorCodeProcessor")
public class SmsCodeProcessor extends AbStractValidatorCodeProcessor<ValidatorCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsSendMessage smsSendMessage;

    @Override
    protected void send(ServletWebRequest request, ValidatorCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsSendMessage.send(mobile, validateCode.getCode());
    }
}
