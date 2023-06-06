import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ConnectionService } from 'src/app/services/connection.service';

@Component({
  selector: 'app-add-date-action',
  templateUrl: './add-date-action.component.html',
  styleUrls: ['./add-date-action.component.scss']
})
export class AddDateActionComponent {

  constructor(public dialogRef: MatDialogRef<AddDateActionComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { repairCode: string },
              private connection:ConnectionService){}

  public addDateActionForm = new FormGroup({
    dateCode: new FormControl("",
    [ Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    repairCode: new FormControl(`${this.data.repairCode}`),
    nameOfDate: new FormControl("", Validators.required),
    date: new FormControl(Date, Validators.required)
  });
  
  addDateAction(){
    const formData = {...this.addDateActionForm.value};

    this.connection.addDateActionToRepair(formData).subscribe(
      (data) => {
        this.dialogRef.close(true)
        this.addDateActionForm.reset();
      },
      () => this.dialogRef.close(false)
    );

    console.log(this.addDateActionForm.value);
  }

}
