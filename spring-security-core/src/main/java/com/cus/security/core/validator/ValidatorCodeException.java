package com.cus.security.core.validator;

import org.springframework.security.core.AuthenticationException;

public class ValidatorCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidatorCodeException(String msg) {
		super(msg);
	}

}