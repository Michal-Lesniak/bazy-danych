import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { ConnectionService } from 'src/app/services/connection.service';
import { Customer } from "../../interfaces/customer";


@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.scss']
})
export class NewCustomerComponent {
  public customersArray: Customer[] = [];
  public messageState = false;
  public message = "";

  constructor(private connection: ConnectionService){}

  public customerForm = new FormGroup({
    userCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    email: new FormControl("", Validators.email),
    phone: new FormControl("", Validators.required),
  });

  // public getUsers() {
  //   this.connection.getCustomers().subscribe((data) => {
  //     this.customersArray = data;
  //   });
  // }

  public addUser() {
    const formData = { ...this.customerForm.value };
    this.connection.addCustomer(formData).subscribe(
      (data) => {
        // this.getUsers();
        this.customerForm.reset();
      },
      // () => this.handleMessage("Użytkownik o podanym kodzie już istnieje")
    );
  }

  // public handleMessage(message: string) {
  //   this.messageState = true;
  //   this.message = message;
  //   this.getUsers();
  //   setTimeout(() => {
  //     this.messageState = false;
  //     this.message = "";
  //   }, 5000);
  // }

  
}

