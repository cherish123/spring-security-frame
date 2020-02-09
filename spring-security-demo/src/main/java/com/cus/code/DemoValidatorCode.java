package com.cus.code;

import com.cus.security.core.validator.ValidatorCodeGenerator;
import com.cus.security.core.validator.ValidatorCode;
import org.springframework.web.context.request.ServletWebRequest;


//@Component("imageCodeGenerate")
public class DemoValidatorCode implements ValidatorCodeGenerator {

    @Override
    public ValidatorCode generateValitorCode(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
