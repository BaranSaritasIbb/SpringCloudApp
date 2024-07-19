package com.SpringCloudApp.service.excel;

import com.SpringCloudApp.dto.excel.ColumnDTO;
import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.enums.ColumnType;
import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.model.Users;
import com.SpringCloudApp.service.ExcelSchemaService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ExcelSchemaService excelSchemaService;

    public String validateExcelFile(MultipartFile file, Long schemaId) throws IOException {

        List<ExcelSchemaDTO> schemas = excelSchemaService.getExcelSchemaAllById(schemaId);
        List<Map<String, Object>> rows = readExcelFile(file,schemas.get(0));
        List<ExcelFieldDTO> fields = schemas.get(0).getColumns();


        for (Map<String, Object> rowData : rows) {
            String validationResult = validateColumns(schemas.get(0).getColumns(), rowData);
            if (!validationResult.equals("dogru islem")) {
                return validationResult;
            }
        }

        List<Users> usersList = mapToUsers(rows, fields);

        return "dogru islem";
    }
    public List<Users> mapToUsers(List<Map<String, Object>> rows, List<ExcelFieldDTO> fields) {
        List<Users> usersList = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            Users user = new Users();
            for (ExcelFieldDTO field : fields) {
                String columnName = field.getColumnName();
                Object value = row.get(columnName);
                switch (field.getField()) {
                    case "firstname":
                        user.setFirstname((String) value);
                        break;
                    case "lastname":
                        user.setLastname((String) value);
                        break;
                    case "age":
                        user.setAge((Integer) value);
                        break;
                    // Diğer alanlar eklenebilir
                }
            }
            usersList.add(user);
        }

        return usersList;
    }

    public List<Map<String, Object>> readExcelFile(MultipartFile file,ExcelSchemaDTO schema) throws IOException {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // Başlık kontrolü
            List<ExcelFieldDTO> columns = schema.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.getCell(i);
                String columnName = headerRow.getCell(0).getStringCellValue();
                if (cell == null || !cell.getStringCellValue().equals(columns.get(i).getColumnName())) {
                    throw new IllegalArgumentException("Başlık " + columns.get(i).getColumnName() + " bekleniyor ama " + (cell == null ? "yok" : cell.getStringCellValue()) + " bulundu.");
                }
            }


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    String columnName = headerRow.getCell(columnIndex).getStringCellValue();

                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.put(columnName, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.put(columnName, cell.getDateCellValue());
                            } else {
                                double numericValue = cell.getNumericCellValue();
                                // Check if the numeric value is an integer
                                if (numericValue == (int) numericValue) {
                                    rowData.put(columnName, (int) numericValue);
                                } else {
                                    rowData.put(columnName, numericValue);
                                }
                            }
                            break;
                        case BOOLEAN:
                            rowData.put(columnName, cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            rowData.put(columnName, cell.getCellFormula());
                            break;
                        case BLANK:
                            rowData.put(columnName, "");
                            break;
                        default:
                            rowData.put(columnName, cell.toString());
                            break;
                    }
                }
                rows.add(rowData);
            }
        }
        return rows;
    }


    public String validateColumns(List<ExcelFieldDTO> columns, Map<String, Object> rowData) {
        for (ExcelFieldDTO column : columns) {
            Object value = rowData.get(column.getColumnName());

            if (column.isRequired() && (value == null || value.toString().isEmpty())) {
                return "Adi: " + rowData.get("firstname") + " olan satirin " + column.getColumnName() + " kolonu dolu olmasi gerekirken bos";
            }

            if (value != null && !isTypeValid(ColumnType.valueOf(column.getColumnType()), value)) {
                return "Adi: " + value + " olan satirin " + column.getColumnName() + " bilgisi " + column.getColumnType() + " tipinde olmasi gerekirken farkli formattadir";
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
