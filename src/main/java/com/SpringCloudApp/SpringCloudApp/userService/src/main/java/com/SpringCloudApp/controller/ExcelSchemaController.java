package com.SpringCloudApp.controller;


import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.service.ExcelSchemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excelSchemas")
@RequiredArgsConstructor
public class ExcelSchemaController {

    private final ExcelSchemaService excelSchemaService;

    @PostMapping
    public ResponseEntity<ExcelSchemaDTO> createExcelSchema(@RequestBody ExcelSchemaDTO excelSchemaDTO) {
        ExcelSchemaDTO createdExcelSchema = excelSchemaService.createExcelSchema(excelSchemaDTO);
        return ResponseEntity.ok(createdExcelSchema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcelSchemaDTO> updateExcelSchema(@PathVariable Long id, @RequestBody ExcelSchemaDTO excelSchemaDTO) {
        ExcelSchemaDTO updatedExcelSchema = excelSchemaService.updateExcelSchema(id, excelSchemaDTO);
        return ResponseEntity.ok(updatedExcelSchema);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcelSchema(@PathVariable Long id) {
        excelSchemaService.deleteExcelSchema(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExcelSchemaDTO>> getExcelSchemaById(@PathVariable Long id) {
        List<ExcelSchemaDTO> excelSchemaDTO = excelSchemaService.getExcelSchemaAllById(id);
        return ResponseEntity.ok(excelSchemaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExcelSchemaDTO>> getAllExcelSchemas() {
        List<ExcelSchemaDTO> excelSchemas = excelSchemaService.getAllExcelSchemas();
        return ResponseEntity.ok(excelSchemas);
    }
}
