package com.farouk.bengarssallah.backend.service;

import com.farouk.bengarssallah.backend.domain.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
	
	  public Mono<Transaction> addTransaction(Transaction transaction);
	  public Mono<Transaction> updatTransaction(Transaction transaction);
	  public Mono<Void> deleteTransaction(long idTransaction);
	  public Mono<Transaction> findTransaction(long idTransaction);
	  public Flux<Transaction> findAllTransactions();
	  
	
}
