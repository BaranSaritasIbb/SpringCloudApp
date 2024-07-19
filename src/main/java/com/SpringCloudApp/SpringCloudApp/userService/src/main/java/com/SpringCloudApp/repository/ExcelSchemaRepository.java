package com.SpringCloudApp.repository;

import com.SpringCloudApp.model.ExcelSchema;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExcelSchemaRepository extends JpaRepository<ExcelSchema,Long> {

    @Query("""
            SELECT e FROM ExcelSchema e WHERE e.id = :id
            """)

    List<ExcelSchema> findByExcelSchemaId(@Param("id") Long id);


}
