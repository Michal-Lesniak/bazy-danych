import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';
import { SearchPipe } from '../pipes/search.pipe';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [
    HeaderComponent,
    SearchPipe,
    
  ],
  imports: [
    CommonModule,
  ],
  exports: [HeaderComponent,
  SearchPipe]
})
export class SharedModule { }
