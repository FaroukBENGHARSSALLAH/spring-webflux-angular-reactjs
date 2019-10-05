import { Component, OnInit } from '@angular/core';
import { Transaction } from 'app/model/Transaction';
import { Company } from 'app/model/Company';
import { TransactionService } from 'app/service/hero.service';

@Component({
  selector: 'app-livechart',
  template: '<fusioncharts  width="500"  height="300"  dataFormat="json"  type="realtimeline"  [dataSource]="data" ></fusioncharts>',
  styleUrls: ['./livechart.component.css'],
  providers: [TransactionService]
})
export class LivechartComponent implements OnInit {

	data: Object;

  constructor() { }

  ngOnInit() {
	  this.data = {
		      chart: { },
		      data: []
		    };
  }

}
