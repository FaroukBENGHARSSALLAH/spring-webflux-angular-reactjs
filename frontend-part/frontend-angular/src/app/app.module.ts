import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CompanyComponent } from './component/company/company.component';
import { NavbarComponent } from './component/navbar/navbar.component';

import { CompanyService } from './services/company.service';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
  CompanyService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
