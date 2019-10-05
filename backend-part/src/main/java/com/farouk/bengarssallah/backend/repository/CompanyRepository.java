package com.farouk.bengarssallah.backend.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;

import reactor.core.publisher.Flux;

public interface CompanyRepository extends ReactiveMongoRepository<Company, Long> {

	@Query("{ 'companyId' : ?0 }")
	public Flux<Transaction> findTransactionsByidCompany(long idCompany);
	 
}
