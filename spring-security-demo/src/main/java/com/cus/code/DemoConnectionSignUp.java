package com.cus.code;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
