package com.SpringCloudApp.service;

import com.SpringCloudApp.dto.excel.ExcelFieldCreateRequest;
import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.model.ExcelField;

import java.util.List;

public interface ExcelFieldService {
    ExcelFieldDTO createExcelField(ExcelFieldCreateRequest excelField);
    ExcelFieldDTO updateExcelField(Long id, ExcelFieldDTO excelField);
    void deleteExcelField(Long id);
    ExcelFieldDTO getExcelFieldById(Long id);
    List<ExcelField> getAllExcelFields();

    List<ExcelFieldDTO> getExcelSchemasById(Long id);
}