package com.SpringCloudApp.dto.request;


import com.SpringCloudApp.dto.excel.ExcelSchemaDTO;
import com.ibb.boot.data.util.aop.TCKimlikNo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String firstname;
    @TCKimlikNo
    private String lastname;
    private Integer age;
    private Date age2;
    private List<ExcelSchemaDTO> excelSchemaList;
    private LocalDate deneme;
    private Date created;
    private Date updated;
    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
