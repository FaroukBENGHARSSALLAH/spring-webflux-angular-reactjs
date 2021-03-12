import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import * as EventSource from 'eventsource';
import { Price } from '../models/price.model';
import * as constants from '../utils/constants';

@Injectable()
export class TransactionService {

     fetch(symbol): Observable<Array<Price>> {
			return Observable.create((observer) => {
				      
					  let eventSource = new EventSource(constants.HOST + constants.TRANSACTIONS_STREAM_URL + symbol);
					  eventSource.onmessage = (event) => {
						    let pricevalue = JSON.parse(event.data).price;
						    date = new Date();
						    observer.next(new Price(date, price));
					  };
					  eventSource.onerror = (error) => {
						    if(eventSource.readyState === 0) {
						       console.log('The stream has been closed by the server.');
						       eventSource.close();
						       observer.complete();
						        } 
						    else {
						      observer.error('EventSource error: ' + error);
						       }
					  }
			});
    }  


}
