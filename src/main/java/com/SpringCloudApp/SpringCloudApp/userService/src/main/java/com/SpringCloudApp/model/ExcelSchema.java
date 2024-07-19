package com.SpringCloudApp.model;


import com.SpringCloudApp.dto.excel.ColumnDTO;
import com.SpringCloudApp.util.helper.JsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExcelSchema {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "excelSchema", cascade = CascadeType.ALL, orphanRemoval = true)
    @Lazy
    private List<ExcelField> columns;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private Users users;
}
