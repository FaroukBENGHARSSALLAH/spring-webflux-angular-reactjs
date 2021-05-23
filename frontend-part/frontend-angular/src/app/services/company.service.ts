import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../utils/constants';

@Injectable()
export class CompanyService {

  constructor(private http:HttpClient) { }
  
  
  public fetchC(){
	 return this.http.get(Constants.HOST + 'api/companies/');
           } 
		   
		   
  public fetch(symbol){
	 return this.http.get(Constants.HOST + 'api/companies/' + symbol);
           }

}
