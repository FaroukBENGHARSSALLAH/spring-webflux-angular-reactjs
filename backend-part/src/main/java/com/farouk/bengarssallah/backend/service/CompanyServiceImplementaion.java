package com.farouk.bengarssallah.backend.service;

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
	public Mono<Void> deleteCompany(String symbol) {
		return companyRepository.deleteById(symbol);
	}

	@Override
	public Mono<Company> findCompany(String symbol) {
		return companyRepository.findById(symbol);
	}
	
	@Override
	public Mono<Company> findBySymbol(String symbol) {
		return companyRepository.findBySymbol(symbol);
	}

	@Override
	public Flux<Company> findAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Flux<Transaction> findTransactionsByidCompany(String symbol) {
	return companyRepository.findTransactionsByidCompany(symbol);
	}

	

	
}
