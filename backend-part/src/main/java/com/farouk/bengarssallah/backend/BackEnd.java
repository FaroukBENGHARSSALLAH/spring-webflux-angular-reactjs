package com.farouk.bengarssallah.backend;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.farouk.bengarssallah.backend.domain.Company;
import com.farouk.bengarssallah.backend.domain.Transaction;
import com.farouk.bengarssallah.backend.repository.CompanyRepository;
import com.farouk.bengarssallah.backend.repository.TransactionRepository;

@SpringBootApplication
public class BackEnd {

    @Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BackEnd.class, args);
	}
	
	

    
    
    
    
    @Bean
    CommandLineRunner start(CompanyRepository companyRepository, TransactionRepository transactionRepository){
    	
        return args->{
                Stream.of("MNA","QAI","WTMF","FTLS", "JPHF").forEach(s->{
                	companyRepository.save(new Company(s, s + "Tracker ETF",(10000+Math.random()*900), LocalDateTime.now()))
                            .subscribe(soc->{
                                System.out.println(soc.toString());
                            });
                });
               transactionRepository.deleteAll().subscribe(null,null,()->{
                   Stream.of("MNA","QAI","WTMF","FTLS", "JPHF").forEach(s->{
                	   companyRepository.findBySymbol(s).subscribe(c ->{
                           for (int i = 0; i <10 ; i++) {
                               Transaction transaction=new Transaction();
                               transaction.setCreationDate(LocalDateTime.now());
                               transaction.setReference("TR"+(10+Math.random()*90));
                               transaction.setCompany(c);
                               transaction.setPrice(1+(Math.random()*12-6)/100);
                               transactionRepository.save(transaction).subscribe(t -> {
                                   System.out.println(t.toString());
                                 });
                           }
                       });

                   });
               });
            System.out.println("......");

        };
    }
    
}
	
	    
