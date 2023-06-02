import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomersComponent } from './customers/customers.component';
import { ApplianceComponent } from './appliance/appliance.component';
import { RepairComponent } from './repair/repair.component';
import { PartComponent } from './part/part.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'customers', component: CustomersComponent},
  { path: 'appliances', component: ApplianceComponent },
  { path: 'repairs', component: RepairComponent},
  { path: 'parts', component: PartComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
