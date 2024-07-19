package com.SpringCloudApp.dto.excel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcelFieldDTO {

    private Long id;
    private String field;
    private String columnName;
    private int index;
    private String columnType;
    private boolean required;
    private Long excelSchemaId;

}