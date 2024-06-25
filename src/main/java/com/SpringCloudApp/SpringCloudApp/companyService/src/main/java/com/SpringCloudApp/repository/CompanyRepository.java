package com.SpringCloudApp.repository;

import com.SpringCloudApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
