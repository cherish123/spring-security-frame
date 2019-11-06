package com.custome.security.core.validator.config;


import com.custome.security.core.properties.SecurityProperties;
import com.custome.security.core.validator.ValidatorCodeGenerator;
import com.custome.security.core.validator.image.ImageGeneratorCode;
import com.custome.security.core.validator.sms.DefaultSmsSendMessage;
import com.custome.security.core.validator.sms.SmsSendMessage;
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
