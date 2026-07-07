package com.billbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billbook.entity.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}