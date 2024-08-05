package com.SpringCloudApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.SpringCloudApp","ist.ibb.boot.security","com.ibb.boot.data"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}