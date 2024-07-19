package com.SpringCloudApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testEndpoint() throws IllegalAccessException {
        throw new IllegalAccessException("Zorunlu hata oluştu!");
    }
}
