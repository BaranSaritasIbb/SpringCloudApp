package com.SpringCloudApp.controller;

import com.SpringCloudApp.dto.excel.ValidationRequestDTO;
import com.SpringCloudApp.service.excel.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final ValidationService validationService;

    @PostMapping("/validate")
    public String validateData(@RequestBody ValidationRequestDTO request) {
        String isValid = validationService.validateColumns(request.getColumns(), request.getRowData());
        return isValid;
    }


}
