package com.SpringCloudApp.util.excel;

import com.SpringCloudApp.dto.excel.ExcelFieldDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericMapper {

    public static <T> List<T> mapToObjects(List<Map<String, Object>> rows, List<ExcelFieldDTO> fields, Class<T> clazz) throws Exception {
        List<T> objectList = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            T obj = clazz.getDeclaredConstructor().newInstance();
            for (ExcelFieldDTO field : fields) {
                String columnName = field.getColumnName();
                Object value = row.get(columnName);
                try {
                    Field classField = clazz.getDeclaredField(field.getField());
                    classField.setAccessible(true);
                    classField.set(obj, value);
                }catch (NoSuchFieldException ignored) { }
            }
            objectList.add(obj);
        }

        return objectList;
    }
}
