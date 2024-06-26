package com.SpringCloudApp.controller;


import com.SpringCloudApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService service;

    @GetMapping
    public String getTest(Authentication authentication){

        return " ";
    }
}
