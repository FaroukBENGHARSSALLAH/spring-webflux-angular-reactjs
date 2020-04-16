package com.farouk.bengarssallah.backend.service;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
	
	  public Mono<Company> addCompany(Company company);
	  public Mono<Company> updateCompany(Company company);
	  public Mono<Void> deleteCompany(String symbol);
	  public Flux<Company> findAllCompanies();
	  public Mono<Company> findCompany(String symbol);
	  public Mono<Company> findBySymbol(String symbol);
	  public Flux<Transaction> findTransactionsByidCompany(String symbol);
	
}
