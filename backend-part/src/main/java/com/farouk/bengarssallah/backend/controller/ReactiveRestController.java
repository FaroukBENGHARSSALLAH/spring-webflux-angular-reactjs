package com.farouk.bengarssallah.backend.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
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
	
	@GetMapping(value = "/companies/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Company> getCompany(@PathVariable long id) {
		return companyService.findCompany(id);
	}
	
	
	@GetMapping(value = "/transatcions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Transaction> getTransactions() {
		return transatcionService.findAllTransactions();
	}
	
	@GetMapping(value = "/transatcions/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Transaction> getTransaction(@PathVariable long id) {
		return transatcionService.findTransaction(id);
	}
	
	@GetMapping(value = "/companies/{id}/transatcions")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Transaction> getTransactionForCompany(@PathVariable long id) {
		return companyService.findTransactionsByidCompany(id);
	}
	
	@PostMapping("/companies")
	@ResponseStatus(HttpStatus.CREATED)
    public Mono<Company> addCompany(@RequestBody Company company){
		return companyService.addCompany(company);
    }
	
	@PutMapping(value = "/companies/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Company> updateCompany(@PathVariable long id, @RequestBody Company company) {
		company.setId(id);
		return companyService.updateCompany(company);
	}
	
	@DeleteMapping(value = "/companies/{id}")
	@ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteCompany(@PathVariable long id){
        return companyService.deleteCompany(id);
    }
	
	
    @PostMapping("/transactions")
    public Mono<Transaction> addTransaction(@RequestBody Transaction transaction){
        return transatcionService.addTransaction(transaction);
    }
    @DeleteMapping(value = "/transactions/{id}")
    public Mono<Void> deleteTransaction(@PathVariable long idTransaction){
        return transatcionService.deleteTransaction(idTransaction);
    }
    @PutMapping("/transactions/{id}")
    public Mono<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable long id){
        transaction.setId(id);
        return transatcionService.updatTransaction(transaction);
    }

    @GetMapping(value = "/transactions/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> streamTransactions(){
        return transatcionService.findAllTransactions();
    }

    @GetMapping(value = "/transactions/stream/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> stream(@PathVariable long id){
        return transatcionService.findTransaction(id).flatMapMany(soc->{
        	
            Flux<Long> interval=Flux.interval(Duration.ofMillis(1000));
            Flux<Transaction> transactionFlux= Flux.fromStream(Stream.generate(()->{
                Transaction transaction=new Transaction();
                transaction.setInstant(Instant.now());
                transaction.setPrice(soc.getPrice()*(1+(Math.random()*12-6)/100));
                return transaction;
            }));
            
            return Flux.zip(interval,transactionFlux)
                    .map(data->{
                        return data.getT2();
                    }).share();
        });
    }


}
