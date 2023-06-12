import { DialogRef } from '@angular/cdk/dialog';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ConnectionService } from 'src/app/services/connection.service';

@Component({
  selector: 'app-new-appliance',
  templateUrl: './new-appliance.component.html',
  styleUrls: ['./new-appliance.component.scss']
})
export class NewApplianceComponent {
  constructor(public dialogRef: MatDialogRef<NewApplianceComponent>, private connection:ConnectionService){}

  public applianceForm = new FormGroup({
    applianceCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    photoURL: new FormControl("", Validators.required)
  });
  
  public addAppliance(){
    const formData = {...this.applianceForm.value};
    this.connection.addAppliance(formData).subscribe(
    (data) => {
      this.dialogRef.close(true)
      this.applianceForm.reset();
    },
    () => this.dialogRef.close(false)
    );
  }

}
