package com.SpringCloudApp.controller;

import com.SpringCloudApp.dto.excel.ExcelFieldCreateRequest;
import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.model.ExcelField;
import com.SpringCloudApp.service.ExcelFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excelFields")
@RequiredArgsConstructor
public class ExcelFieldController {

    private final ExcelFieldService excelFieldService;

    @PostMapping
    public ResponseEntity<ExcelFieldDTO> createExcelField(@RequestBody ExcelFieldCreateRequest excelField) {
        ExcelFieldDTO createdExcelField = excelFieldService.createExcelField(excelField);
        return ResponseEntity.ok(createdExcelField);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcelFieldDTO> updateExcelField(
            @PathVariable Long id,
            @RequestBody ExcelFieldDTO excelField
    ) {
        ExcelFieldDTO updatedExcelField = excelFieldService.updateExcelField(id, excelField);
        return ResponseEntity.ok(updatedExcelField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcelField(@PathVariable Long id) {
        excelFieldService.deleteExcelField(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExcelFieldDTO> getExcelFieldById(@PathVariable Long id) {
        ExcelFieldDTO excelField = excelFieldService.getExcelFieldById(id);
        return ResponseEntity.ok(excelField);
    }
    /*@GetMapping("/schema/{id}")
    public ResponseEntity<List<ExcelFieldDTO>> getExcelSchemasById(@PathVariable Long id) {
        List<ExcelFieldDTO> excelField = excelFieldService.getExcelSchemasById(id);
        return ResponseEntity.ok(excelField);
    }*/

    @GetMapping
    public ResponseEntity<List<ExcelField>> getAllExcelFields() {
        List<ExcelField> excelFields = excelFieldService.getAllExcelFields();
        return ResponseEntity.ok(excelFields);
    }
}
