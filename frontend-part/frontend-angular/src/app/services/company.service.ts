import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class CompanyService {

   private host:string = 'http://localhost:8080/';

  constructor(private http:HttpClient) { }
  
  
  public fetchC(){
	 return this.http.get(this.host + 'companies/');
           } 
		   
		   
  public fetch(symbol){
	 return this.http.get(this.host + 'companies/' + symbol);
           }

}
