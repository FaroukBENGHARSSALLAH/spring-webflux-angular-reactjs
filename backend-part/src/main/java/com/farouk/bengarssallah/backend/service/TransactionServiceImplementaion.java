package com.farouk.bengarssallah.backend.service;

import org.springframework.stereotype.Service;

import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.repository.TransactionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImplementaion implements TransactionService {
	
	private final TransactionRepository transactionRepository;

	public TransactionServiceImplementaion(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Mono<Transaction> addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Mono<Transaction> updatTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Mono<Void> deleteTransaction(long idTransaction) {
		return transactionRepository.deleteById(idTransaction);
	}

	@Override
	public Mono<Transaction> findTransaction(long idTransaction) {
		return transactionRepository.findById(idTransaction);
	}

	@Override
	public Flux<Transaction> findAllTransactions() {
		return transactionRepository.findAll();
	}


	
}
