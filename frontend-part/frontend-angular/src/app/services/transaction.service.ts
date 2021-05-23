import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import * as EventSource from 'eventsource';
import { Constants } from '../utils/constants';

@Injectable()
export class TransactionService {

     private eventSource: EventSource;
	 
     fetch(symbol): Observable<Array<number>> {
			return Observable.create((observer) => {
				      
					  this.eventSource = new EventSource(Constants.HOST + Constants.TRANSACTIONS_STREAM_URL + symbol);
					  this.eventSource.onmessage = (event) => {
						    let pricevalue = JSON.parse(event.data).price;
						    observer.next(pricevalue);
					  };
					  this.eventSource.onerror = (error) => {
						    if(this.eventSource.readyState === 0) {
						       console.log('The stream has been closed by the server.');
						       this.eventSource.close();
						        } 
						    else {
						      observer.error('EventSource error: ' + error);
							  this.eventSource.close();
						       }
					  }
			});
         }  
		 
		 
	 terminate(): void {
		   this.eventSource.close();
	    }

}
