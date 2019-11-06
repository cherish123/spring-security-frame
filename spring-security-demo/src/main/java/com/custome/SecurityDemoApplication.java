package com.custome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@MapperScan("com.custome.mapper")
public class SecurityDemoApplication {
    public static void main(String args[]) {
        SpringApplication.run(SecurityDemoApplication.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
