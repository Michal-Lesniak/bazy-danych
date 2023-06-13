import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplianceComponent } from '../appliance/appliance.component';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NewApplianceComponent } from '../appliance/new-appliance/new-appliance.component';
import { SharedModule } from './shared.module';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [ApplianceComponent,
  NewApplianceComponent],
  imports: [
    SharedModule,
    CommonModule, 
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatIconModule
  ],
  exports: [  ]
})
export class AppliancesModule { }
