import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartComponent } from '../part/part.component';
import { SharedModule } from './shared.module';
import { NewPartComponent } from '../part/new-part/new-part.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [ PartComponent, 
  NewPartComponent],
  imports: [
    SharedModule,
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSelectModule,
    MatIconModule,
  ]
})
export class PartsModule { }
