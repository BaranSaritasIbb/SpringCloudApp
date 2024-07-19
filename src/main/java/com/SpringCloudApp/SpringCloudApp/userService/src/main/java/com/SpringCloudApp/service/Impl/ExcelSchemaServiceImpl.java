package com.SpringCloudApp.service.Impl;


import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.exception.LocalException;
import com.SpringCloudApp.model.ExcelField;
import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.repository.ExcelSchemaRepository;
import com.SpringCloudApp.repository.UserRepository;
import com.SpringCloudApp.service.ExcelFieldService;
import com.SpringCloudApp.service.ExcelSchemaService;
import com.ibb.boot.data.exception.BusinessException;
import com.ibb.boot.data.exception.reasons.BusinessExceptionReason;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Expectation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelSchemaServiceImpl implements ExcelSchemaService {


    private final ExcelSchemaRepository excelSchemaRepository;
    private final ModelMapper modelMapper;
    private final UserRepository usersRepository;

    @Override
    public ExcelSchemaDTO createExcelSchema(ExcelSchemaDTO excelSchemaDTO) {
        ExcelSchema excelSchema = convertToEntity(excelSchemaDTO);
        excelSchema.getColumns().forEach(column -> column.setExcelSchema(excelSchema));
        ExcelSchema savedSchema = excelSchemaRepository.save(excelSchema);
        return modelMapper.map(savedSchema,ExcelSchemaDTO.class);
    }

    @Override
    public ExcelSchemaDTO updateExcelSchema(Long id, ExcelSchemaDTO excelSchemaDTO) {
        Optional<ExcelSchema> existingSchema = excelSchemaRepository.findById(id);
        if (existingSchema.isPresent()) {
            ExcelSchema excelSchema = convertToEntity(excelSchemaDTO);
            excelSchema.setId(id);
            excelSchema.getColumns().forEach(column -> column.setExcelSchema(excelSchema));
            ExcelSchema updatedSchema = excelSchemaRepository.save(excelSchema);
            return modelMapper.map(updatedSchema, ExcelSchemaDTO.class);
        } else {
            throw new RuntimeException("ExcelSchema not found");
        }
    }

    @Override
    public void deleteExcelSchema(Long id) {
        excelSchemaRepository.deleteById(id);
    }



    @Override
    public List<ExcelSchemaDTO> getExcelSchemaAllById(Long id) {
        List<ExcelSchema> excelSchemas = excelSchemaRepository.findByExcelSchemaId(id);

        return excelSchemas.stream().map(schema -> {

            List<ExcelField> sortedColumns = schema.getColumns().stream()
                    .sorted(Comparator.comparingInt(ExcelField::getIndex))
                    .toList();


            ExcelSchemaDTO dto = modelMapper.map(schema, ExcelSchemaDTO.class);
            dto.setColumns(sortedColumns.stream()
                    .map(column -> modelMapper.map(column, ExcelFieldDTO.class))
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public ExcelSchema getExcelSchemaById(Long id) {
       return excelSchemaRepository.findById(id)
                .orElseThrow(()->new BusinessException(LocalException.EXCEL_SCHEMA_NOT_FOUND_BY_ID));

    }

    @Override
    public void checkDublicateRequest(Long excelSchemaId,String fieldName){
        ExcelSchema excelSchema = getExcelSchemaById(excelSchemaId);

        excelSchema.getColumns().stream().forEach(i->{
            if(Objects.equals(i.getField().toLowerCase(), fieldName.toLowerCase())){
                // aciklamayi duzelt dublicate veriye izin verilmez tarzi yada zaten var
                throw new BusinessException(BusinessExceptionReason.DATABASE_RECORD_NOT_FOUND);
            }
        });
    }
    @Override
    public List<ExcelSchemaDTO> getAllExcelSchemas() {
        List<ExcelSchema> excelSchemas = excelSchemaRepository.findAll();
        List<ExcelSchemaDTO> excelSchemaDTOList = new ArrayList<>();
         excelSchemas.forEach(i-> {
            ExcelSchemaDTO excelSchemaDTO= modelMapper.map(i, ExcelSchemaDTO.class);
            excelSchemaDTO.setUserId(i.getUsers().getId());
            excelSchemaDTOList.add(excelSchemaDTO);
        });

        return excelSchemaDTOList;
    }

    private ExcelSchema convertToEntity(ExcelSchemaDTO excelSchemaDTO) {
        ExcelSchema excelSchema = new ExcelSchema();
        excelSchema.setColumns(excelSchemaDTO.getColumns().stream()
                .map(fieldDTO -> {
                    ExcelField field = modelMapper.map(fieldDTO,ExcelField.class);
                    field.setExcelSchema(excelSchema); // Set the reference here
                    return field;
                })
                .collect(Collectors.toList()));
        excelSchema.setUsers(usersRepository.findById(excelSchemaDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        return excelSchema;
    }


}
