import { Component } from '@angular/core';
import { OnInit } from '@angular/core';

import { Subscription } from 'rxjs';

import { Transaction } from 'app/model/Transaction';
import { Company } from 'app/model/Company';
import { TransactionService } from 'app/service/hero.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css'],
  providers: [TransactionService]
})
export class TransactionComponent implements OnInit {

	companies: Company[];
    transactions: Transaction[];
    scompany: Company;
    subscription: Subscription;

   constructor(private transactionService: TransactionService) { }

   ngOnInit(): void {
		  this.getcompanies();
	     }

   getCompanies(): void {
		  this.transactionService.getcompanies().then(companies => this.companies = companies);
		  this.subscription = this.transactionService.getcompanies()
		    .subscribe(
		      data => this.companies = data,
		      err => console.log(err)
		    );
	  }
       
       fetch(company: Company): void {
    	   scompany = company;
    	   this.subscription =  this.postService.getPost(company.id))
    	      .subscribe(
    	        (data) => this.transactions = data,
    	        (err) => console.log(err)
    	      );
   	   }

}
