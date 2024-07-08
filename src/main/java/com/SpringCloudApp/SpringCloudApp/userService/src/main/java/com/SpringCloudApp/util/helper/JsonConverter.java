package com.SpringCloudApp.util.helper;

import com.SpringCloudApp.dto.excel.ColumnDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class JsonConverter implements AttributeConverter<List<ColumnDTO>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<ColumnDTO> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list to JSON string.", e);
        }
    }

    @Override
    public List<ColumnDTO> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, ColumnDTO.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to list.", e);
        }
    }
}
