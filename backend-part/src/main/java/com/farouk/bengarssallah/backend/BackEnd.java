package com.farouk.bengarssallah.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
    	Map<String, String> map = new HashMap<>();
    	map.put("DIA", "SPDR Dow Jones Industrial Average ETF");
    	map.put("AGG", "iShares Core US Aggregate Bond ETF");
    	map.put("ASHR", "Deutsche X-trackers Harvest CSI300 CHN A");
    	map.put("AMJ", "JPMorgan Alerian MLP ETN");
    	map.put("XME", "SPDR S&P Metals and Mining ETF");
        return args->{
                Stream.of("DIA","AGG","ASHR","AMJ", "XME").forEach(s->{
                	companyRepository.save(new Company(s, map.get(s), "PCX",
                			new BigDecimal((10000+Math.random()*900)).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                			new BigDecimal((10+Math.random()*900)).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
                			LocalDateTime.now()))
                            .subscribe(soc->{
                                System.out.println(soc.toString());
                            });
                });
               transactionRepository.deleteAll().subscribe(null,null,()->{
                   Stream.of("DIA","AGG","ASHR","AMJ", "XME").forEach(s->{
                	   companyRepository.findBySymbol(s).subscribe(c ->{
                           for (int i = 0; i <10 ; i++) {
                               Transaction transaction=new Transaction();
                               transaction.setCreationDate(LocalDateTime.now());
                               transaction.setReference("TR"+(10+Math.random()*90));
                               transaction.setCompany(c);
                               transaction.setPrice(c.getDay1Flow() + new BigDecimal((10+Math.random()*900)).setScale(2, RoundingMode.HALF_UP).doubleValue());
                               transactionRepository.save(transaction).subscribe(t -> {
                                   
                                 });
                           }
                       });

                   });
               });

        };  
    } 
    
}
	
	    
