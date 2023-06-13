import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { ConnectionService } from 'src/app/services/connection.service';
import { Customer } from "../../interfaces/customer";
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.scss']
})
export class NewCustomerComponent {
  public customersArray: Customer[] = [];
  public messageState = false;
  public message = "";

  constructor(private connection: ConnectionService,
    public dialogRef: MatDialogRef<NewCustomerComponent> ){}

  public customerForm = new FormGroup({
    userCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    email: new FormControl("", Validators.email),
    phone: new FormControl("", Validators.required),
  });



  public addUser() {
    const formData = { ...this.customerForm.value };
    this.connection.addCustomer(formData).subscribe(
      (data) => {
        this.dialogRef.close(true)
        this.customerForm.reset();
      },
      () => this.dialogRef.close(false)
    );
  }



  
}

