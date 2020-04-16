package com.farouk.bengarssallah.backend.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {

	@Query("{ 'companyId' : ?0 }")
	public Flux<Transaction> findTransactionsByidCompany(String symbol);
	
	public Mono<Company> findBySymbol(String symbol);
	 
}
