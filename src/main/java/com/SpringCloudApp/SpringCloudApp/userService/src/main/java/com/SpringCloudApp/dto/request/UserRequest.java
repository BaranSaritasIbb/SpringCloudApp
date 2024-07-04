package com.SpringCloudApp.dto.request;


import com.SpringCloudApp.util.aop.date.DateFormatCheck;
import com.SpringCloudApp.util.helper.date.ValidDateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
   // @DateFormatCheck(pattern = "yyyy-MM-dd", message = "Invalid date format for field")
    private Date age2;
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
