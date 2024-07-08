package com.SpringCloudApp.service;

import com.SpringCloudApp.model.ExcelField;

import java.util.List;

public interface ExcelFieldService {
    ExcelField createExcelField(ExcelField excelField);
    ExcelField updateExcelField(Long id, ExcelField excelField);
    void deleteExcelField(Long id);
    ExcelField getExcelFieldById(Long id);
    List<ExcelField> getAllExcelFields();
}