import React, {Component} from 'react';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import * as EventSource from 'eventsource';


class Transaction extends Component {
	
	   constructor() {
            super();
			this.dispose = this.dispose.bind(this);

       }
	   
	   
	   componentDidMount(){
		    let chart = am4core.create("chartdiv", am4charts.XYChart);
			chart.hiddenState.properties.opacity = 0;
			chart.padding(0, 0, 0, 0);
			chart.zoomOutButton.disabled = true;
			let dateAxis = chart.xAxes.push(new am4charts.DateAxis());
			dateAxis.renderer.grid.template.location = 0;
			dateAxis.renderer.minGridDistance = 30;
			dateAxis.dateFormats.setKey("second", "ss");
			dateAxis.periodChangeDateFormats.setKey("second", "[bold]h:mm a");
			dateAxis.periodChangeDateFormats.setKey("minute", "[bold]h:mm a");
			dateAxis.periodChangeDateFormats.setKey("hour", "[bold]h:mm a");
			dateAxis.renderer.inside = true;
			dateAxis.renderer.axisFills.template.disabled = false;
			dateAxis.renderer.ticks.template.disabled = false;
			let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
			valueAxis.tooltip.disabled = true;
			valueAxis.interpolationDuration = 500;
			valueAxis.rangeChangeDuration = 500;
			valueAxis.renderer.inside = true;
			valueAxis.renderer.minLabelPosition = 0.05;
			valueAxis.renderer.maxLabelPosition = 0.95;
			valueAxis.renderer.axisFills.template.disabled = false;
			valueAxis.renderer.ticks.template.disabled = false;
			let series = chart.series.push(new am4charts.LineSeries());  
			series.dataFields.dateX = "date";
			series.dataFields.valueY = "value";
			series.interpolationDuration = 500;
			series.defaultState.transitionDuration = 0;
			series.tensionX = 0.8;
			let title = chart.titles.create();
			title.text = "Changement de prix par temps";
			title.fontSize = 25;
			title.marginBottom = 30;
			dateAxis.interpolationDuration = 500;
			dateAxis.rangeChangeDuration = 500;
			series.fillOpacity = 1;
			let gradient = new am4core.LinearGradient();
			gradient.addColor(chart.colors.getIndex(0), 0.2);
			gradient.addColor(chart.colors.getIndex(0), 0);
			series.fill = gradient;
			dateAxis.renderer.labels.template.adapter.add("fillOpacity", function (fillOpacity, target) {
				let dataItem = target.dataItem;
				return dataItem.position;
			});
			dateAxis.events.on("validated", function () {
				am4core.iter.each(dateAxis.renderer.labels.iterator(), function (label) {
					label.fillOpacity = 1;
				})
			})
			dateAxis.renderer.labels.template.adapter.add("rotation", function (rotation, target) {
				let dataItem = target.dataItem;
				if (dataItem.date && dataItem.date.getTime() === am4core.time.round(new Date(dataItem.date.getTime()), "minute").getTime()) {
					target.verticalCenter = "middle";
					target.horizontalCenter = "left";
					return -90;
				}
				else {
					target.verticalCenter = "bottom";
					target.horizontalCenter = "middle";
					return 0;
				}
			});
			let bullet = series.createChild(am4charts.CircleBullet);
			bullet.circle.radius = 5;
			bullet.fillOpacity = 1;
			bullet.fill = chart.colors.getIndex(0);
			bullet.isMeasured = false;
			series.events.on("validated", function() {
				bullet.moveTo(series.dataItems.last.point);
				bullet.validatePosition();
			});
		   let date = new Date();
		   let eventSource = new EventSource('http://localhost:8080//companies/transactions/stream/' + this.props.symbol); 
		   eventSource.onopen = (event: any) => console.log('open', event); 
		   eventSource.onmessage = (event: any) => {
			  date = new Date(date.getTime() + 1000);
				 chart.addData({ 
					   date: date, 
					   value: event.data.price 
						   },1); 
			       };
			eventSource.onerror = (event: any) => console.log('error', event);
		    this.dispose = () =>  {
                     chart.dispose();
			};	  
	          }
			
       dispose = () =>  {};
	   
	   
	    componentWillUnmount() {
		  this.dispose();
		  
     }
   
   
	render(){	   
		  return (
			<div id="chartdiv" style={{ width: "100%", height: "500px" }}></div>
		  );
		}
    }

export default Transaction;