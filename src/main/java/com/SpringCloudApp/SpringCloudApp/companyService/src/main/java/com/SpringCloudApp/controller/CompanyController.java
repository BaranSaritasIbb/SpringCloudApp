package com.SpringCloudApp.controller;


import com.SpringCloudApp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService service;

}
