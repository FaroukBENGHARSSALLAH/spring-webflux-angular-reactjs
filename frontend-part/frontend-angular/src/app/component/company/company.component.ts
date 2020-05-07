import { Component, OnInit } from '@angular/core';
import { Company } from '.../models/company.model';
import { CompanyService } from '.../services/company.service';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

   private company: Company;	 
   private symbol: string = '';	
   private companies: Company[];
   
  constructor(private companyService:CompanyService) { }

  ngOnInit() {
	  this.companyService.fetchC()
		    .subscribe((res: any) => {
					 this.companies = res.data;
				 });
  }
  
  
  fetch(symbol){
			  this.symbol = symbol;
	          this.companyService.fetch(this.symbol)
			      .subscribe((res: any) => {
					 this.company = res;
				 });
           }
		   
		   
		   
		    back(){
		    this.company = null;
                  }

}
