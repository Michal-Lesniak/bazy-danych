import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomersComponent } from '../customers/customers.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [CustomersComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [CustomersComponent]
})
export class CustomersModule { }
