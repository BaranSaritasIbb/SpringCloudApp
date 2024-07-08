package com.SpringCloudApp.dto.excel;

import com.SpringCloudApp.enums.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.annotation.Aspect;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ColumnDTO {
    private String columnName;
    private ColumnType columnType;
    private boolean required;
}
