package com.SpringCloudApp.model;


import com.SpringCloudApp.enums.ColumnType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelField {

    @Id
    @GeneratedValue
    private Long Id;
    private String columnName;
    private String  columnType;
    private boolean required;
    @ManyToOne
    @JoinColumn(name="excel_schema_id",referencedColumnName = "id")
    private ExcelSchema excelSchema;
}
