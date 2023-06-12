import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RepairComponent } from '../repair/repair.component';
import { NewRepairComponent } from '../repair/new-repair/new-repair.component';
import { BaseRepairComponent } from '../repair/base-repair/base-repair.component';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { AddDateActionComponent } from '../repair/base-repair/add-date-action/add-date-action.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import { SharedModule } from './shared.module';


@NgModule({
  declarations: [RepairComponent,
    NewRepairComponent,
    BaseRepairComponent,
    AddDateActionComponent],
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    SharedModule
  ]
})
export class RepairModule { }
