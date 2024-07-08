package com.SpringCloudApp.service.Impl;

import com.SpringCloudApp.model.ExcelField;
import com.SpringCloudApp.repository.ExcelFieldRepository;
import com.SpringCloudApp.service.ExcelFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcelFieldServiceImpl implements ExcelFieldService {

    @Autowired
    private ExcelFieldRepository excelFieldRepository;

    @Override
    public ExcelField createExcelField(ExcelField excelField) {
        return excelFieldRepository.save(excelField);
    }

    @Override
    public ExcelField updateExcelField(Long id, ExcelField excelField) {
        Optional<ExcelField> existingField = excelFieldRepository.findById(id);
        if (existingField.isPresent()) {
            excelField.setId(id);
            return excelFieldRepository.save(excelField);
        } else {
            throw new RuntimeException("ExcelField not found");
        }
    }

    @Override
    public void deleteExcelField(Long id) {
        excelFieldRepository.deleteById(id);
    }

    @Override
    public ExcelField getExcelFieldById(Long id) {
        return excelFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ExcelField not found"));
    }

    @Override
    public List<ExcelField> getAllExcelFields() {
        return excelFieldRepository.findAll();
    }
}