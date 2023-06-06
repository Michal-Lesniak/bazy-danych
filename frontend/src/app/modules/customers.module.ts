import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomersComponent } from '../customers/customers.component';
import {MatIconModule} from '@angular/material/icon';
import { NewCustomerComponent } from '../customers/new-customer/new-customer.component';
import { SharedModule } from './shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';




@NgModule({
  declarations: [CustomersComponent,
  NewCustomerComponent],
  imports: [
    SharedModule,
    CommonModule,
    MatIconModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ],
  exports: [CustomersComponent,
  ]
})
export class CustomersModule { }
