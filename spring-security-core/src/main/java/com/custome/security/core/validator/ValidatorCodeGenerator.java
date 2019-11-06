package com.custome.security.core.validator;

import org.springframework.web.context.request.ServletWebRequest;


public interface ValidatorCodeGenerator {

    public ValidatorCode generateValitorCode(ServletWebRequest request);
}
