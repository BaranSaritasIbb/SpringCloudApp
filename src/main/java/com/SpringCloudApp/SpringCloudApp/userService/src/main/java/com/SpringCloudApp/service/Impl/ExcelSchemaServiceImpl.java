package com.SpringCloudApp.service.Impl;

import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.repository.ExcelSchemaRepository;
import com.SpringCloudApp.service.ExcelSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcelSchemaServiceImpl implements ExcelSchemaService {

    @Autowired
    private ExcelSchemaRepository excelSchemaRepository;

    @Override
    public ExcelSchema createExcelSchema(ExcelSchema excelSchema) {
        return excelSchemaRepository.save(excelSchema);
    }

    @Override
    public ExcelSchema updateExcelSchema(Long id, ExcelSchema excelSchema) {
        Optional<ExcelSchema> existingSchema = excelSchemaRepository.findById(id);
        if (existingSchema.isPresent()) {
            excelSchema.setId(id);
            return excelSchemaRepository.save(excelSchema);
        } else {
            throw new RuntimeException("ExcelSchema not found");
        }
    }

    @Override
    public void deleteExcelSchema(Long id) {
        excelSchemaRepository.deleteById(id);
    }

    @Override
    public ExcelSchema getExcelSchemaById(Long id) {
        return excelSchemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ExcelSchema not found"));
    }

    @Override
    public List<ExcelSchema> getAllExcelSchemas() {
        return excelSchemaRepository.findAll();
    }
}
