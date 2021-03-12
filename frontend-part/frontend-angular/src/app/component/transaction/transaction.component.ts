import { Component, Inject, NgZone, PLATFORM_ID, Input} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

import * as am4core from '@amcharts/amcharts4/core';
import * as am4charts from '@amcharts/amcharts4/charts';
import am4themes_animated from '@amcharts/amcharts4/themes/animated';

import { TransactionService } from '.../services/transaction.service';
import { Observable } from 'rxjs/Observable';
import { Price } from '.../models/price.model';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent  {
	
  private chart: am4charts.XYChart;
  @Input('price')
  private price: number;
  @Input('symbol')
  private symbol: string;
  private priceObservable: Observable<Price>;

  constructor(@Inject(PLATFORM_ID) private platformId, private zone: NgZone, private transactionService: TransactionService) {}


  browserOnly(f: () => void) {
    if (isPlatformBrowser(this.platformId)) {
      this.zone.runOutsideAngular(() => {
            f();
        });
      }
  }

  ngAfterViewInit() {
    
    this.browserOnly(() => {
		
            am4core.useTheme(am4themes_animated);
            this.chart = am4core.create("chartdiv", am4charts.XYChart);
			this.chart.hiddenState.properties.opacity = 0;
			this.chart.padding(0, 0, 0, 0);
			this.chart.zoomOutButton.disabled = true;
			
			let dateAxis = this.chart.xAxes.push(new am4charts.DateAxis());
			dateAxis.renderer.grid.template.location = 0;
			dateAxis.renderer.minGridDistance = 30;
			dateAxis.dateFormats.setKey("second", "ss");
			dateAxis.periodChangeDateFormats.setKey("second", "[bold]h:mm a");
			dateAxis.periodChangeDateFormats.setKey("minute", "[bold]h:mm a");
			dateAxis.periodChangeDateFormats.setKey("hour", "[bold]h:mm a");
			dateAxis.renderer.inside = true;
			dateAxis.renderer.axisFills.template.disabled = false;
			dateAxis.renderer.ticks.template.disabled = false;

			let valueAxis = this.chart.yAxes.push(new am4charts.ValueAxis());
			valueAxis.tooltip.disabled = true;
			valueAxis.interpolationDuration = 500;
			valueAxis.rangeChangeDuration = 500;
			valueAxis.renderer.inside = true;
			valueAxis.renderer.minLabelPosition = 0.05;
			valueAxis.renderer.maxLabelPosition = 0.95;
			valueAxis.renderer.axisFills.template.disabled = false;
			valueAxis.renderer.ticks.template.disabled = false;

			let series = this.chart.series.push(new am4charts.LineSeries());  
			series.dataFields.dateX = "date";
			series.dataFields.valueY = "value";
			series.interpolationDuration = 500;
			series.defaultState.transitionDuration = 0;
			series.tensionX = 0.8;
		
			
			let title = this.chart.titles.create();
			title.text = "Changement de prix par temps";
			title.fontSize = 25;
			title.marginBottom = 30;

			dateAxis.interpolationDuration = 500;
			dateAxis.rangeChangeDuration = 500;

			series.fillOpacity = 1;
			let gradient = new am4core.LinearGradient();
			gradient.addColor(this.chart.colors.getIndex(0), 0.2);
			gradient.addColor(this.chart.colors.getIndex(0), 0);
			series.fill = gradient;

			
			dateAxis.renderer.labels.template.adapter.add("fillOpacity", function (fillOpacity, target) {
				let dataItem = target.dataItem;
				return dataItem.position;
			});
			dateAxis.events.on("validated", function () {
				am4core.iter.each(dateAxis.renderer.labels.iterator(), function (label) {
					label.fillOpacity = 1;
				})
			}); 
			let bullet = series.createChild(am4charts.CircleBullet);
			bullet.circle.radius = 5;
			bullet.fillOpacity = 1;
			bullet.fill = this.chart.colors.getIndex(0);
			bullet.isMeasured = false;

			series.events.on("validated", function() {
				bullet.moveTo(series.dataItems.last.point);
				bullet.validatePosition();
			});
			
			let data = [];
			data.push({ date: new Date(), value: this.price });
			chart.data = data;
			
			
			priceObservable = this.transactionService.fetch(this.symbol);
			
			
			quoteObservable.subscribe(cprice => {
                      chart.addData([{ 
					                  date:  cprice.date, 
					                  value: cprice.price  
						                  }]); 
             });
    });
  }

  ngOnDestroy() {
    this.browserOnly(() => {
      if (this.chart) {
	      this.chart.dispose();
		  this.observer.complete();
      }
    });
  }
}
