package com.billbook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.billbook.dto.request.CompanyRequest;
import dto.response.ApiResponse;
import com.billbook.dto.response.CompanyResponse;
import com.billbook.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
@Validated
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
            @Valid @RequestBody CompanyRequest request) {

        CompanyResponse company = companyService.createCompany(request);

        ApiResponse<CompanyResponse> response =
                new ApiResponse<>(
                        true,
                        "Company created successfully",
                        company);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAllCompanies() {

        List<CompanyResponse> companies = companyService.getAllCompanies();

        ApiResponse<List<CompanyResponse>> response =
                new ApiResponse<>(
                        true,
                        "Companies fetched successfully",
                        companies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyById(
            @PathVariable Long id) {

        CompanyResponse company = companyService.getCompanyById(id);

        ApiResponse<CompanyResponse> response =
                new ApiResponse<>(
                        true,
                        "Company fetched successfully",
                        company);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        CompanyResponse company = companyService.updateCompany(id, request);

        ApiResponse<CompanyResponse> response =
                new ApiResponse<>(
                        true,
                        "Company updated successfully",
                        company);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(
            @PathVariable Long id) {

        companyService.deleteCompany(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        true,
                        "Company deleted successfully",
                        null);

        return ResponseEntity.ok(response);
    }
}