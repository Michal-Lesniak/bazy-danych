import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ConnectionService } from 'src/app/services/connection.service';
import { Appliance } from 'src/app/interfaces/appliance';


@Component({
  selector: 'app-new-part',
  templateUrl: './new-part.component.html',
  styleUrls: ['./new-part.component.scss']
})
export class NewPartComponent implements OnInit{
  public appliances:Appliance[] = []
  public messageState = false;
  public message = "";

  constructor(public dialogRef: MatDialogRef<NewPartComponent>, private connection:ConnectionService){}

  ngOnInit(): void {
    this.connection.getAppliances().subscribe( data => this.appliances = data);
  }

  public partForm = new FormGroup({
    partCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    price: new FormControl("", [Validators.required, Validators.pattern(/^\d+$/)]),
    applianceCode: new FormControl("", Validators.required),
    photoURL: new FormControl("", Validators.required)
  });
  
  public addPart(){
    const formData = {...this.partForm.value};
    console.log(formData);
    this.connection.addPart(formData).subscribe(
    (data) => {
      this.dialogRef.close(true)
      this.partForm.reset();
    },
    () => this.dialogRef.close(false)
    );
  }
}
