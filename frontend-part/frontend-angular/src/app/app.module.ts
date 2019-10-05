import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FusionChartsModule } from 'angular-fusioncharts';


import { AppComponent } from './app.component';
import { AccountComponent } from './component/account/account/account.component';
import { ClientComponent } from './component/client/client/client.component';
import { TransactionComponent } from './component/transaction/transaction/transaction.component';
import { SecurityComponent } from './component/security/security.component';

//Load FusionCharts
import * as FusionCharts from 'fusioncharts';
// Load Charts module
import * as Charts from 'fusioncharts/fusioncharts.charts';
// Load fusion theme
import * as FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import { LivechartComponent } from './component/livechart/livechart.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    ClientComponent,
    TransactionComponent,
    SecurityComponent,
    LivechartComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
