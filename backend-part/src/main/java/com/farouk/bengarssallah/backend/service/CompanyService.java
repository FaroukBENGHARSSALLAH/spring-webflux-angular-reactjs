package com.farouk.bengarssallah.backend.service;

import java.util.List;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
	
	  public Mono<Company> addCompany(Company company);
	  public Mono<Company> updateCompany(Company company);
	  public Mono<Void> deleteCompany(long idCompany);
	  public Flux<Company> findAllCompanies();
	  public Mono<Company> findCompany(long idCompany);
	  public Flux<Transaction> findTransactionsByidCompany(long idCompany);
	
}
