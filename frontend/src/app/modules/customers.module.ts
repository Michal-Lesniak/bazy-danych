import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomersComponent } from '../customers/customers.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { NewCustomerComponent } from '../customers/new-customer/new-customer.component';




@NgModule({
  declarations: [CustomersComponent,
  NewCustomerComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDialogModule,
  ],
  exports: [CustomersComponent,
  ]
})
export class CustomersModule { }
