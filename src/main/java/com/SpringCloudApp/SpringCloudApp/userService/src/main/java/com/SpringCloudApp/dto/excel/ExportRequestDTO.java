package com.SpringCloudApp.dto.excel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExportRequestDTO {
    private List<ColumnDTO> columns;
    private List<Map<String, Object>> data;

}
