package com.SpringCloudApp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String id ;

    private String username;

    private String name;

    private String surname;

    private String email;

    private Date birthDate;

    public String getNameSurname() {
        return this.name + " " + this.surname;
    }
}
