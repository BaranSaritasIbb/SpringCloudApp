package com.SpringCloudApp.service.Impl;

import com.SpringCloudApp.dto.excel.ExcelFieldCreateRequest;
import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.model.ExcelField;
import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.repository.ExcelFieldRepository;
import com.SpringCloudApp.service.ExcelFieldService;
import com.SpringCloudApp.service.ExcelSchemaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelFieldServiceImpl implements ExcelFieldService {

    private final ExcelFieldRepository excelFieldRepository;
    private final ModelMapper modelMapper;
    private final ExcelSchemaService excelSchemaService;

    @Override
    public ExcelFieldDTO createExcelField(ExcelFieldCreateRequest excelField) {
        ExcelSchema field = excelSchemaService.getExcelSchemaById(excelField.getExcelSchemaId());
        excelSchemaService.checkDublicateRequest(excelField.getExcelSchemaId(),excelField.getField());
        ExcelField field1 = modelMapper.map(excelField,ExcelField.class);
       int fieldIndex = field.getColumns().size();
       field1.setIndex(fieldIndex + 1);
       return modelMapper.map(excelFieldRepository.save(field1),ExcelFieldDTO.class);

    }



    @Override
    public ExcelFieldDTO updateExcelField(Long SchemaId, ExcelFieldDTO excelField) {
        ExcelSchema field = excelSchemaService.getExcelSchemaById(SchemaId);
        AtomicReference<ExcelField> updateExcelField = new AtomicReference<>(new ExcelField());
        field.getColumns().stream().forEach(i->{
            if(Objects.equals(excelField.getId(), i.getId())){
                updateExcelField.set(excelFieldRepository.save(modelMapper.map(excelField, ExcelField.class)));
            }
        });


        ExcelFieldDTO fieldDTO = modelMapper.map(updateExcelField.get(),ExcelFieldDTO.class);
        return fieldDTO;
    }

    @Override
    public void deleteExcelField(Long id) {
        excelFieldRepository.deleteById(id);
    }

    @Override
    public ExcelFieldDTO getExcelFieldById(Long id) {
        ExcelField excelField = excelFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ExcelField not found"));
        ExcelFieldDTO excelFieldDTO = modelMapper.map(excelField,ExcelFieldDTO.class);
        excelFieldDTO.setExcelSchemaId(excelField.getExcelSchema().getId());
         return excelFieldDTO;
    }

    @Override
    public List<ExcelField> getAllExcelFields() {
        return excelFieldRepository.findAll();
    }

    @Override
    public List<ExcelFieldDTO> getExcelSchemasById(Long id) {
        List<ExcelField> excelField = excelFieldRepository.findDTOByExcelSchemaId(id);

        return excelField.stream().map(i->{
            return modelMapper.map(i,ExcelFieldDTO.class);
        }).collect(Collectors.toList());

    }
}