package com.custome.security.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 搜索依赖，查找校验流程，image or sms
 */
@Component
public class ValidatorCodeProcessorHolder {

	@Autowired
	private Map<String, ValidatorCodeProcessor> validatorCodeProcessors;

	public ValidatorCodeProcessor findValidateCodeProcessor(ValidatorCodeType type) {
		return findValidatorCodeProcessor(type.toString().toLowerCase());
	}

	public ValidatorCodeProcessor findValidatorCodeProcessor(String type) {
		//拼凑出smsValidatorCodeProcessor等的名字。搜索依赖的map中的key
		String name = type.toLowerCase() + ValidatorCodeProcessor.class.getSimpleName();
		ValidatorCodeProcessor processor = validatorCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidatorCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}