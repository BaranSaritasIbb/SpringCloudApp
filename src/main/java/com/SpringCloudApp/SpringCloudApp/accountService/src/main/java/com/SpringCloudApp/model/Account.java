package com.SpringCloudApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Setter
    @Column(name = "uname")
    private String username;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "surname")
    private String surname;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "birth_date")
    private Date birthDate;

    @Setter
    @Column(name = "pwd")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_active")
    private Boolean active;


}
