package com.SpringCloudApp.service.excel;

import com.SpringCloudApp.dto.excel.ColumnDTO;
import com.SpringCloudApp.enums.ColumnType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ValidationService {

    public String validateColumns(List<ColumnDTO> columns, Map<String, Object> rowData) {
        Map<String, Object> actualRowData = (Map<String, Object>) rowData.get("data");


        for (ColumnDTO column : columns) {
            Object value = actualRowData.get(column.getColumnName());

            if (column.isRequired() && value == null) {
                return "Adi :" + actualRowData.get("name")+" olan satirin dolu olmasi gerekirken bos";
            }

            if (value != null && !isTypeValid(column.getColumnType(), value)) {
                return "Adi :" + actualRowData.get("name")+" olan satirin " +actualRowData.get(column.getColumnName())+" bilgisi " +column.getColumnType() + " tipinde olmasi gerekirken farkli formattadir";
            }
        }
        return "dogru islem";
    }
    private boolean isTypeValid(ColumnType columnType, Object value) {
        switch (columnType) {
            case METIN:
                return value instanceof String;
            case TAM_SAYI:
                return value instanceof Integer;
            case MANTIKSAL:
                return value instanceof Boolean;
            case ONDALIK:
                return value instanceof Double;
            case FIYAT:
                return value instanceof BigDecimal;
            case TARIH:
                return value instanceof java.util.Date || value instanceof java.time.LocalDate;
            case ZAMAN_DAMGASI:
                return value instanceof java.util.Date || value instanceof java.time.LocalDateTime || value instanceof java.sql.Timestamp;
            case UZUN:
                return value instanceof Long;
            case KÜSURATLI:
                return value instanceof Float;
            default:
                throw new IllegalArgumentException("Desteklenmeyen sütun tipi: " + columnType);
        }
    }

}
