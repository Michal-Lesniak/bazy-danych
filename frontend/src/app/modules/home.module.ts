import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home/home.component';
import { HomeTileComponent } from '../home-tile/home-tile.component';



@NgModule({
  declarations: [
    HomeComponent,
    HomeTileComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HomeComponent
  ]
})
export class HomeModule { }
