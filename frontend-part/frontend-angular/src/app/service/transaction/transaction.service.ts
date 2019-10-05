import { Injectable } from '@angular/core';
import { Headers, Http } from "@angular/http";
import { Observable } from 'rxjs';

@Injectable()
export class TransactionService {

	private baseUrl = 'http://localhost:8080/hero-backend-0/hero';

	const host = 'http//localhost:8080/'
  constructor(private http: Http) {}
  
  
  getCompanies(): Observable<any> {
	  return this.http.get(host + '/companies');
  }
  
  getCompanyTransaction(id: number): Observable<any> {
	    return this.http.get(host + '/companies/' + number + '/transatcions');
	  }


}
