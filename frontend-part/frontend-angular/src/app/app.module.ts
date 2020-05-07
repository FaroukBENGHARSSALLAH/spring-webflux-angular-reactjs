import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CompanyComponent } from './component/company/company.component';
import { TransactionComponent } from './component/transaction/transaction.component';
import { NavbarComponent } from './component/navbar/navbar.component';

import { CompanyService } from './services/company.service';
import { TransactionService } from './services/transaction.service';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    NavbarComponent,
    TransactionComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
  CompanyService,
  TransactionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
