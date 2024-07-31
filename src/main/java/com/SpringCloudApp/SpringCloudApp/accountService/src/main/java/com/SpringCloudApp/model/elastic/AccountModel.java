package com.SpringCloudApp.model.elastic;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Document(indexName = "account")
public class AccountModel {
    @Id
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
}
