import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeModule } from './modules/home.module';
import { SharedModule } from './modules/shared.module';
import { ConnectionService } from './services/connection.service';
import { HttpClientModule } from '@angular/common/http';
import { CustomersModule } from './modules/customers.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NewCustomerComponent } from './customers/new-customer/new-customer.component';
import { RepairComponent } from './repair/repair.component';
import { ApplianceComponent } from './appliance/appliance.component';
import { PartComponent } from './part/part.component';
import { PartsModule } from './modules/parts.module';
import { AppliancesModule } from './modules/appliances.module';
import { RepairModule } from './modules/repair.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HomeModule,
    CustomersModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    PartsModule,
    AppliancesModule,
    RepairModule
  ],
  providers: [ConnectionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
