import { Component, OnInit } from '@angular/core';
import { Appliance } from '../interfaces/appliance';
import { MatTableDataSource } from '@angular/material/table';
import { ConnectionService } from '../services/connection.service';
import { MatDialog } from "@angular/material/dialog";
import { NewApplianceComponent } from './new-appliance/new-appliance.component';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-appliance',
  templateUrl: './appliance.component.html',
  styleUrls: ['./appliance.component.scss']
})
export class ApplianceComponent implements OnInit {
  appliances: Appliance[] = [];
  selectedItem: Appliance | null = null;
  searchText = '';
  public messageStateError = false;
  public messageStateComplete = false;
  public message = "";

  constructor(private connection: ConnectionService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAppliances();
  }


  deleteAppliance(appliance: Appliance) {
    this.connection.deleteAppliance(appliance.applianceCode).subscribe((res) => {
      if (res) {
        this.handleMessage(false, "Pomyślnie Usunięto!");
        this.getAppliances();
        this.selectedItem = null;
      }
    }, () => this.handleMessage(true, "Wystąpił błąd w trakcie usuwania!"))
  };

  getAppliances() {
    this.connection.getAppliances().subscribe(
      data => this.appliances = data
    )
  }

  public openDialog(): void {
    const newApplianceRef = this.dialog.open(NewApplianceComponent, {
      backdropClass: 'backdropDialog',
    });

    newApplianceRef.afterClosed().subscribe(result => {

      if(result === true){
        this.handleMessage(false, "Urządzenie zostało pomyślnie dodane.")
      }else if(result === false){
        this.handleMessage(true, "Urządzenie o podanym kodzie już istnieje.")
      }
      this.getAppliances()
    });
  }

  handleMessage(error: boolean, message: string) {
    if (error) {
      this.messageStateError = true;
    } else  {
      this.messageStateComplete = true;
    }
    this.message = message;
    setTimeout(() => {
      this.messageStateError = false;
      this.messageStateComplete = false;
    }, 3000)
  }
}
