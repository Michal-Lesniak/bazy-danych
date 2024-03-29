import { Component, OnInit } from '@angular/core';
import { Repair } from 'src/app/interfaces/repair';
import { RepairDetails } from 'src/app/interfaces/repair-details';
import { RepairStatus } from 'src/app/interfaces/repair-status';
import { ConnectionService } from 'src/app/services/connection.service';
import { MatDialog } from "@angular/material/dialog";
import { AddDateActionComponent } from './add-date-action/add-date-action.component';
import { searchOption } from 'src/app/interfaces/searchOption';
import { take } from 'rxjs';

@Component({
  selector: 'app-base-repair',
  templateUrl: './base-repair.component.html',
  styleUrls: ['./base-repair.component.scss']
})
export class BaseRepairComponent implements OnInit {
  repairsArray: RepairDetails[] = [];
  public messageStateError = false;
  public messageStateComplete = false;
  public message = "";
  public options: searchOption[] = [
   {code: 1, name: "Nazwa Klienta"},
   {code: 2, name: "Data"},
   {code: 3, name: "Status"}];
  public optionCode?: number;
  public customerName = "";
  public status!:boolean;
  public date: Date | null = null;  

  constructor(private connection:ConnectionService, private dialog: MatDialog){}

  ngOnInit(): void {
   this.getRepairsDetails();
  }

  getRepairsDetails(){
    this.connection.getRepairsDetails().pipe(take(1)).subscribe( data => {
      this.repairsArray = data;
    });
  }

  getPartsName(repair:RepairDetails){
    return repair.parts.map(part => part.name).join(", ");
  }

  statusName(repair:RepairDetails){
    if(repair.status) return "Gotowe"
    else return "W realizacji"
  }


  getLatestDate(repair:RepairDetails){
    return repair.dateActionDtoList.reduce(
      (latest, action) => 
      latest.date > action.date ? latest : action
    )
  }

  changeStatus(status:boolean, repairCode:string){
    const repairStatus:RepairStatus = {
      status: status,
      repairCode: repairCode
    }
    this.connection.updateStatus(repairStatus).subscribe(state => {
      if(state){
        this.getRepairsDetails();
        this.handleMessage(false, "Status pomyślnie zmieniony.");
      }else{
        this.handleMessage(true, "Wystąpił błąd")
      }
    })
  }

  public openDialog(repairCode:string): void {
    const newDateActionDialogRef = this.dialog.open(AddDateActionComponent, {
      backdropClass: 'backdropDialog',
      data: { repairCode: repairCode}
    });

    newDateActionDialogRef.afterClosed().subscribe(result => {

      if(result === true){
        this.handleMessage(false, "Zdarzenie zostało poprawnie dodane.")
      }else if(result  === false){
        this.handleMessage(true, "Wystąpił błąd")
      }
      this.getRepairsDetails()
    });
  }


  handleMessage(isError:boolean, message:string){
    if (isError) {
      this.messageStateError = true;
    } else  {
      this.messageStateComplete = true;
    }
    this.message = message;
    setTimeout(() => {
      this.messageStateError = false;
      this.messageStateComplete = false;
    }, 5000)
  }


}
