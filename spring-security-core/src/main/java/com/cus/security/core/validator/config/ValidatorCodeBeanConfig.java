package com.cus.security.core.validator.config;


import com.cus.security.core.properties.SecurityProperties;
import com.cus.security.core.validator.ValidatorCodeGenerator;
import com.cus.security.core.validator.image.ImageGeneratorCode;
import com.cus.security.core.validator.sms.DefaultSmsSendMessage;
import com.cus.security.core.validator.sms.SmsSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidatorCodeGenerator")
    public ValidatorCodeGenerator imageValidatorCodeGenerator() {
        ImageGeneratorCode imageCodeGenerate = new ImageGeneratorCode();
        imageCodeGenerate.setSecurityProperties(securityProperties);

        return imageCodeGenerate;
    }

    @Bean
    @ConditionalOnMissingBean(SmsSendMessage.class)
    public SmsSendMessage smsSendMessage() {
        return new DefaultSmsSendMessage();
    }
}
