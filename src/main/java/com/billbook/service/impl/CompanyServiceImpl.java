package com.billbook.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.billbook.dto.request.CompanyRequest;
import com.billbook.dto.response.CompanyResponse;
import com.billbook.entity.company.Company;
import com.billbook.exception.ResourceNotFoundException;
import com.billbook.mapper.CompanyMapper;
import com.billbook.repository.CompanyRepository;
import com.billbook.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository repository;
	private final CompanyMapper mapper;

	public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public CompanyResponse createCompany(CompanyRequest request) {

		Company company = mapper.toEntity(request);

		Company saved = repository.save(company);

		return mapper.toResponse(saved);
	}

	@Override
	public CompanyResponse updateCompany(Long id, CompanyRequest request) {

		Company company = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));

		mapper.updateEntity(company, request);

		Company updated = repository.save(company);

		return mapper.toResponse(updated);
	}

	@Override
	public CompanyResponse getCompanyById(Long id) {

		Company company = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));

		return mapper.toResponse(company);
	}

	@Override
	public List<CompanyResponse> getAllCompanies() {

		return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public void deleteCompany(Long id) {

		Company company = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));

		repository.delete(company);
	}
}