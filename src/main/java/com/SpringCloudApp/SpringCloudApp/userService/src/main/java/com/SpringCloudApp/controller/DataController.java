package com.SpringCloudApp.controller;


import com.SpringCloudApp.service.excel.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final ValidationService validationService;

    @PostMapping("/validate/{schemaId}")
    public String validateData(@PathVariable Long schemaId , @RequestParam("file") MultipartFile file) throws IOException {
        return validationService.validateExcelFile(file, schemaId);

    }


}
