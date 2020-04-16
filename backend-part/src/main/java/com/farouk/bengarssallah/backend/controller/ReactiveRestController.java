package com.farouk.bengarssallah.backend.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.service.CompanyService;
import com.farouk.bengarssallah.backend.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
@RestController
@RequestMapping("/api")
public class ReactiveRestController {

	private final CompanyService companyService;
	private final TransactionService transatcionService;

	public ReactiveRestController(CompanyService companyService, TransactionService transatcionService) {
		this.companyService = companyService;
		this.transatcionService = transatcionService;
	}

	@GetMapping(value = "/companies")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Company> getCompanies() {
		return companyService.findAllCompanies();
	}
	
	@GetMapping(value = "/companies/{symbol}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Company> getCompany(@PathVariable String symbol) {
		return companyService.findBySymbol(symbol);
	}
	
	
	@GetMapping(value = "/transactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Transaction> getTransactions() {
		return transatcionService.findAllTransactions();
	}
	
	@GetMapping(value = "/transactions/{symbol}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Transaction> getTransaction(@PathVariable String symbol) {
		return transatcionService.findTransaction(symbol);
	}
	
	@GetMapping(value = "/companies/{symbol}/transactions",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<Transaction> getTransactionForCompany(@PathVariable String symbol) {
		return companyService.findTransactionsByidCompany(symbol);
	}
	
	@PostMapping("/companies")
	@ResponseStatus(HttpStatus.CREATED)
    public Mono<Company> addCompany(@RequestBody Company company){
		return companyService.addCompany(company);
    }
	
	@PutMapping(value = "/companies/{symbol}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Company> updateCompany(@PathVariable String symbol, @RequestBody Company company) {
		company.setSymbol(symbol);
		return companyService.updateCompany(company);
	}
	
	@DeleteMapping(value = "/companies/{symbol}")
	@ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteCompany(@PathVariable String symbol){
        return companyService.deleteCompany(symbol);
    }
	
	
    @PostMapping("/transactions")
    public Mono<Transaction> addTransaction(@RequestBody Transaction transaction){
        return transatcionService.addTransaction(transaction);
    }
    @DeleteMapping(value = "/transactions/{symbol}")
    public Mono<Void> deleteTransaction(@PathVariable String reference){
        return transatcionService.deleteTransaction(reference);
    }
    @PutMapping("/transactions/{reference}")
    public Mono<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable String reference){
        transaction.setReference(reference);
        return transatcionService.updatTransaction(transaction);
    }

   

    @GetMapping(value = "/companies/transactions/stream/{symbol}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> stream(@PathVariable String symbol){
        return companyService.findBySymbol(symbol).flatMapMany( c ->{
        	
            Flux<Long> interval=Flux.interval(Duration.ofMillis(1000));
            Flux<Transaction> transactionFlux= Flux.fromStream(Stream.generate(()->{
                Transaction transaction=new Transaction();
                transaction.setCreationDate(LocalDateTime.now());
                transaction.setCompany(c);
                transaction.setReference("TR"+(10+Math.random()*90));
                transaction.setPrice(1+(Math.random()*12-6)/100);
                return transaction;
            }));
            
            return Flux.zip(interval, transactionFlux)
                    .map(data->{
                        return data.getT2();
                    }).share();
        });
    }


}
