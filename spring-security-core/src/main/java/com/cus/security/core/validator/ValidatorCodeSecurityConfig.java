package com.cus.security.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Component("validatorCodeSecurityConfig")
public class ValidatorCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private Filter validatorCodeFilter;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(validatorCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
	}
	
}