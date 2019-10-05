package com.farouk.bengarssallah.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner  {

    @Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	
	
    @Override
    public void run(String... strings) throws Exception {
        companyRepository.subscribe(null,null,()->{
		   societeRepository.save(new Societe(1, 0, "QAI","IQ Hedge Multi-Strategy Tracker ETF",10000+Math.random()*900), LocalDateTime.now()).subscribe(c->{});
		   societeRepository.save(new Societe(2, 0, "MNA","IQ Merger Arbitrage ETF",10000+Math.random()*900), LocalDateTime.now()).subscribe(c->{});
		   societeRepository.save(new Societe(3, 0, "WTMF","WisdomTree Managed Futures Strategy Fund",10000+Math.random()*900), LocalDateTime.now()).subscribe(c->{});
		   societeRepository.save(new Societe(4, 0, "FTLS","First Trust Long/Short Equity Fund",10000+Math.random()*900), LocalDateTime.now()).subscribe(c->{});
		   societeRepository.save(new Societe(4, 0, "JPHF","JPMorgan Diversified Alternatives ETF",10000+Math.random()*900), LocalDateTime.now()).subscribe(c->{});
            });
			
			
		transactionRepository.deleteAll().subscribe(null,null,()->{
                   Stream.of(1, 2, 3, 4, 5).forEach(s->{
                       companyRepository.findCompany(s).subscribe(c->{
                           for (int i = 0; i <10 ; i++) {
                               Transaction transaction = new Transaction();
                               transaction.setInstant(Instant.now());
                               transaction.setCompany(c);
                               transaction.setPrice(c.getPrice()*(1+(Math.random()*12-6)/100));
                               transactionRepository.save(transaction).subscribe(t->{});
                           }
                       });

                   });
               });
    }
	
	    
