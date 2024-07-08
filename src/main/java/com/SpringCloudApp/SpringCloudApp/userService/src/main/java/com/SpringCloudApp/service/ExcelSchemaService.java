package com.SpringCloudApp.service;

import com.SpringCloudApp.model.ExcelSchema;

import java.util.List;

public interface ExcelSchemaService {
    ExcelSchema createExcelSchema(ExcelSchema excelSchema);
    ExcelSchema updateExcelSchema(Long id, ExcelSchema excelSchema);
    void deleteExcelSchema(Long id);
    ExcelSchema getExcelSchemaById(Long id);
    List<ExcelSchema> getAllExcelSchemas();
}