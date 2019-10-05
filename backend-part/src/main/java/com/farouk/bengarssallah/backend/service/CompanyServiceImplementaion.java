package com.farouk.bengarssallah.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.repository.CompanyRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyServiceImplementaion implements CompanyService {
	
	private final CompanyRepository companyRepository;

	public CompanyServiceImplementaion(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Mono<Company> addCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Mono<Company> updateCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Mono<Void> deleteCompany(long idCompany) {
		return companyRepository.deleteById(idCompany);
	}

	@Override
	public Mono<Company> findCompany(long idCompany) {
		return companyRepository.findById(idCompany);
	}

	@Override
	public Flux<Company> findAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Flux<Transaction> findTransactionsByidCompany(long idCompany) {
	return companyRepository.findTransactionsByidCompany(idCompany);
	}

	
}
