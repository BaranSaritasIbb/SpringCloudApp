package com.SpringCloudApp.repository;

import com.SpringCloudApp.dto.excel.ExcelFieldDTO;
import com.SpringCloudApp.model.ExcelField;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcelFieldRepository extends JpaRepository<ExcelField, Long> {

    @Query("""
            SELECT e
            FROM ExcelField e
            WHERE e.excelSchema.id = :id
            """)
    List<ExcelField> findDTOByExcelSchemaId(@Param("id") Long id);
}
