package com.custome.security.core.validator.sms;

import com.custome.security.core.properties.SecurityProperties;
import com.custome.security.core.validator.ValidatorCode;
import com.custome.security.core.validator.ValidatorCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidatorCodeGenerator")
public class SmsValidatorCodeGenator implements ValidatorCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidatorCode generateValitorCode(ServletWebRequest request) {

        String numericCode = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidatorCode(numericCode, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
