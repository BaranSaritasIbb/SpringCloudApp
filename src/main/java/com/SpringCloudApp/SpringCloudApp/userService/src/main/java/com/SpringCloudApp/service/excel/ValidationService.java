package com.SpringCloudApp.service.excel;

import com.SpringCloudApp.dto.excel.ColumnDTO;
import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.SpringCloudApp.dto.request.KullaniciDTO;
import com.SpringCloudApp.dto.request.UserRequest;
import com.SpringCloudApp.enums.ColumnType;
import com.SpringCloudApp.model.ExcelSchema;
import com.SpringCloudApp.model.Users;
import com.SpringCloudApp.service.ExcelSchemaService;
import com.SpringCloudApp.util.excel.GenericMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Örneğin, tarih formatı


    private final ExcelSchemaService excelSchemaService;


    public String validateExcelFile(MultipartFile file, Long schemaId) throws Exception {

        List<ExcelSchemaDTO> schemas = excelSchemaService.getExcelSchemaAllById(schemaId);
        //Userin kaydettigi field bilgilerini sirali bir sekilde donduren degisken
        List<ExcelFieldDTO> columns = schemas.get(0).getColumns();
        List<Map<String, Object>> rows = readExcelFile(file,schemas.get(0));
        List<ExcelFieldDTO> fields = schemas.get(0).getColumns();

        String validationResult = validateExcel(columns , rows);
        List<KullaniciDTO> usersList = GenericMapper.mapToObjects(rows, fields, KullaniciDTO.class);

        return "dogru islem";
    }


    public String validateExcel(List<ExcelFieldDTO> columns,List<Map<String, Object>> rows){
        for (Map<String, Object> rowData : rows) {
            String validationResult = validateColumns(columns, rowData);
            if (!validationResult.equals("dogru islem")) {
                return validationResult;
            }
        }
        return "dogru";
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

            int rowCounter = 0;
            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.getCell(i);
                if(cell != null){

                String columnName = headerRow.getCell(rowCounter).getStringCellValue();
                if (cell == null || !cell.getStringCellValue().equalsIgnoreCase(columns.get(rowCounter).getColumnName())) {
                    throw new IllegalArgumentException("Başlık " + columns.get(i).getColumnName() + " bekleniyor ama " + (cell == null ? "yok" : cell.getStringCellValue()) + " bulundu.");
                }
                    rowCounter++;
                }
            }


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                int cellIndex = 0;
                Row row = sheet.getRow(i);
                Map<String, Object> rowData = new HashMap<>();
                boolean check = checkIfRowIsEmpty(row);
                if(!check) {
                    for (Cell cell : row) {
                        int columnIndex = cell.getColumnIndex();
                        String columnName = headerRow.getCell(columnIndex).getStringCellValue();

                        switch (cell.getCellType()) {
                            case STRING:
                                String colName = columns.get(cellIndex).getColumnType();
                                String cellValue = cell.getStringCellValue();
                                if (isDate(cellValue) && Objects.equals(colName, "TARIH")) {
                                    try {
                                        Date date = dateFormat.parse(cellValue);
                                        rowData.put(columnName, date);
                                    } catch (ParseException e) {
                                        // Tarih formatı hatası
                                        e.printStackTrace();
                                    }
                                } else {
                                    rowData.put(columnName, cellValue);
                                }
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
                        cellIndex++;
                    }
                    rows.add(rowData);
                }

            }
        }
        return rows;
    }
    private boolean isDate(String value) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat.toPattern());
            df.setLenient(false);
            df.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    private boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
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
