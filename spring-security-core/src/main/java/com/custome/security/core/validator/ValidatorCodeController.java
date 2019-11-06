package com.custome.security.core.validator;

import com.custome.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidatorCodeController<validatorCodeProcessors> {

    @Autowired
    private ValidatorCodeProcessorHolder validatorCodeProcessorHolder;


    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidatorCodeProcessor}接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void generateCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validatorCodeProcessorHolder.findValidatorCodeProcessor(type).create(new ServletWebRequest(request, response));
    }


}
