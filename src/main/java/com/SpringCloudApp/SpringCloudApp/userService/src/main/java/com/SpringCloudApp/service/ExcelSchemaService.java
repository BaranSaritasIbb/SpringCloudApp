package com.SpringCloudApp.service;


import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.model.ExcelSchema;

import java.util.List;

public interface ExcelSchemaService {
    ExcelSchemaDTO createExcelSchema(ExcelSchemaDTO excelSchemaDTO);
    ExcelSchemaDTO updateExcelSchema(Long id, ExcelSchemaDTO excelSchemaDTO);
    void deleteExcelSchema(Long id);
    void checkDublicateRequest(Long excelSchemaId,String fieldName);
    ExcelSchema getExcelSchemaById(Long id);
    List<ExcelSchemaDTO> getExcelSchemaAllById(Long id);
    List<ExcelSchemaDTO> getAllExcelSchemas();
}
