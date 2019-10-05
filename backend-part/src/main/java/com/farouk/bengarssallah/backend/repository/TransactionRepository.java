package com.farouk.bengarssallah.backend.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.farouk.bengarssallah.backend.domain.Transaction;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Long> {


}
