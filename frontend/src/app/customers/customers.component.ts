import { NewCustomerComponent } from './new-customer/new-customer.component';
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConnectionService } from "../services/connection.service";
import { Customer } from "../interfaces/customer";
import { MatDialog } from "@angular/material/dialog";

@Component({
  selector: "app-customers",
  templateUrl: "./customers.component.html",
  styleUrls: ["./customers.component.scss"],
})
export class CustomersComponent implements OnInit {
  public customersArray: Customer[] = [];
  public messageStateError = false;
  public messageStateComplete = false;
  public message = "";
  public showForm = false;

  constructor(private connection: ConnectionService, public dialog:MatDialog) {}

  ngOnInit() {
    this.getUsers();
  }

  public customerForm = new FormGroup({
    userCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    email: new FormControl("", Validators.email),
    phone: new FormControl("", Validators.required),
  });

  public getUsers() {
    this.connection.getCustomers().subscribe((data) => {
      this.customersArray = data;
    });
  }


  public deleteUser(index: number) {
    const userCode = this.customersArray[index].userCode;
    this.connection.deleteCustomer(userCode).subscribe((state) => {
      if (state) {
        this.handleMessage(false, "Usunięto");
      }
    }, () => this.handleMessage(true, "Wystąpił błąd podczas usuwania"));
  }

  public handleMessage(isError:boolean, message: string) {
    if(isError){
      this.messageStateError = true;
    }else this.messageStateComplete = true;
    this.message = message;
    this.getUsers();
    setTimeout(() => {
      this.messageStateError = false;
      this.messageStateComplete = false;

      this.message = "";
    }, 5000);
  }

  public openDialog(): void {
    const newCustomerRef = this.dialog.open(NewCustomerComponent, {
      backdropClass: 'backdropDialog',
    });

    newCustomerRef.afterClosed().subscribe(() => this.getUsers());
  }
}
