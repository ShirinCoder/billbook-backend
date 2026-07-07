package com.billbook.service;

import java.util.List;

import com.billbook.dto.request.CompanyRequest;
import com.billbook.dto.response.CompanyResponse;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest request);

    CompanyResponse updateCompany(Long id, CompanyRequest request);

    CompanyResponse getCompanyById(Long id);

    List<CompanyResponse> getAllCompanies();

    void deleteCompany(Long id);
}