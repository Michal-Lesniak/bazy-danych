import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { addRepair } from 'src/app/interfaces/addRepair';
import { Appliance } from 'src/app/interfaces/appliance';
import { Customer } from 'src/app/interfaces/customer';
import { Part } from 'src/app/interfaces/part';
import { ConnectionService } from 'src/app/services/connection.service';

@Component({
  selector: 'app-new-repair',
  templateUrl: './new-repair.component.html',
  styleUrls: ['./new-repair.component.scss']
})
export class NewRepairComponent implements OnInit {

  constructor(private connection:ConnectionService){}

  customerArray: Customer[] = [];
  applianceArray: Appliance[] = [];
  partArray: Part[] = [];
  repairCode:string = "";
  dateCode: string = "";
  nameOfDate: string = "";
  date:Date | null = null;

  public repairForm = new FormGroup({
    repairCode: new FormControl("", [ 
    Validators.required,
    Validators.pattern(/^\d+$/)]),
    status: new FormControl(false),
    customerCode: new FormControl("", Validators.required),
    applianceCode: new FormControl("", Validators.required),
    partsCode: new FormControl([],Validators.required),
    dateCode: new FormControl("", Validators.required),
    nameOfDate: new FormControl("", Validators.required),
    date: new FormControl(new Date(), Validators.required)
  });


  ngOnInit(): void {
      this.connection.getCustomers().subscribe((data) => this.customerArray = data);
      this.connection.getAppliances().subscribe((data) => this.applianceArray = data);
      this.connection.getParts().subscribe((data) => this.partArray = data);
  }


  addRepair(){
    const repairFormValue = {...this.repairForm.value};

    const newRepair: addRepair = {
      repairCode: repairFormValue.repairCode!,
      status: repairFormValue.status!,
      customerCode: Number(repairFormValue.customerCode!),
      applianceCode: Number(repairFormValue.applianceCode!),
      partCodes: repairFormValue.partsCode!, 
      dateActionDtoList: [
          {
              dateCode: repairFormValue.dateCode!,
              repairCode: repairFormValue.repairCode!,
              nameOfDate: repairFormValue.nameOfDate!,
              date: repairFormValue.date!
          }
      ]
  };

    this.connection.addRepair(newRepair).subscribe(
      data => console.log(data)
    )
  }

}
