package com.billbook.mapper;

import org.springframework.stereotype.Component;

import com.billbook.dto.request.CompanyRequest;
import com.billbook.dto.response.CompanyResponse;
import com.billbook.entity.company.Company;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest request) {

        Company company = new Company();

        company.setCompanyName(request.getCompanyName());
        company.setAddress(request.getAddress());
        company.setPhone(request.getPhone());
        company.setEmail(request.getEmail());
        company.setGstNumber(request.getGstNumber());
        company.setLogo(request.getLogo());
        company.setFooter(request.getFooter());

        return company;
    }

    public CompanyResponse toResponse(Company company) {

        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setCompanyName(company.getCompanyName());
        response.setAddress(company.getAddress());
        response.setPhone(company.getPhone());
        response.setEmail(company.getEmail());
        response.setGstNumber(company.getGstNumber());
        response.setLogo(company.getLogo());
        response.setFooter(company.getFooter());
        response.setCreatedAt(company.getCreatedAt());
        response.setUpdatedAt(company.getUpdatedAt());

        return response;
    }

    public void updateEntity(Company company, CompanyRequest request) {

        company.setCompanyName(request.getCompanyName());
        company.setAddress(request.getAddress());
        company.setPhone(request.getPhone());
        company.setEmail(request.getEmail());
        company.setGstNumber(request.getGstNumber());
        company.setLogo(request.getLogo());
        company.setFooter(request.getFooter());
    }
}