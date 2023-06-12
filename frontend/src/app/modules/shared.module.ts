import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { SearchPipe } from '../pipes/search.pipe';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FilterByCustomerNamePipe } from '../pipes/filter-by-customer-name.pipe';
import { FilterByStatusPipe } from '../pipes/filter-by-status.pipe';
import { FilterByDatePipe } from '../pipes/filter-by-date.pipe';



@NgModule({
  declarations: [
    HeaderComponent,
    SearchPipe,
    FilterByCustomerNamePipe,
    FilterByStatusPipe,
    FilterByDatePipe
  ],
  imports: [
    CommonModule,
  ],
  exports: [HeaderComponent,
  SearchPipe,
  FilterByCustomerNamePipe,
  FilterByStatusPipe,
  FilterByDatePipe]
})
export class SharedModule { }
