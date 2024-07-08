package com.SpringCloudApp.repository;

import com.SpringCloudApp.model.ExcelField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelFieldRepository extends JpaRepository<ExcelField, Long> {
}
