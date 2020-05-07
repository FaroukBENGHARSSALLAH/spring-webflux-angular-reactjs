import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import * as EventSource from 'eventsource';
import { Transaction } from '../models/transaction.model';

@Injectable()
export class TransactionService {

  private host:string = 'http://localhost:8080/';
    

     fetch(symbol, chart): Observable<Array<Transaction>> {
			let transactions: Observable<Transaction[]>;
			return Observable.create((observer) => {
				      
					  let eventSource = new EventSource(host + '/companies/transactions/stream/' + symbol);
					  eventSource.onmessage = (event) => {
						console.debug('Received event: ', event);
						this.transactions.push(event.data);
						date = new Date(date.getTime() + 1000);
						 chart.addData({ 
							   date: date, 
							   value: event.data.price 
								   },1); 
						   };
						observer.next(this.transactions);
					  };
					  eventSource.onerror = (error) => {
						if(eventSource.readyState === 0) {
						  console.log('The stream has been closed by the server.');
						  eventSource.close();
						  observer.complete();
						} else {
						  observer.error('EventSource error: ' + error);
						}
					  }
			});
    }  


}
