import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConnectionService } from "../services/connection.service";
import { Customer } from "../interfaces/customer";

@Component({
  selector: "app-customers",
  templateUrl: "./customers.component.html",
  styleUrls: ["./customers.component.scss"],
})
export class CustomersComponent implements OnInit {
  public customersArray: Customer[] = [];
  public messageState = false;
  public message = "";
  public showForm = false;

  constructor(private connection: ConnectionService) {}

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

  public addUser() {
    const formData = { ...this.customerForm.value };
    this.connection.addCustomer(formData).subscribe(
      (data) => {
        this.getUsers();
        this.customerForm.reset();
        this.showForm = !this.showForm;
      },
      () => this.handleMessage("Użytkownik o podanym kodzie już istnieje")
    );
  }

  public deleteUser(index: number) {
    const userCode = this.customersArray[index].userCode;
    this.connection.deleteCustomer(userCode).subscribe((state) => {
      if (state) {
        this.handleMessage("Usunięto");
      }
    }, () => this.handleMessage("Wystąpił błąd podczas usuwania"));
  }

  public handleMessage(message: string) {
    this.messageState = true;
    this.message = message;
    this.getUsers();
    setTimeout(() => {
      this.messageState = false;
      this.message = "";
    }, 5000);
  }

  public showFormHandle() {
    this.showForm = !this.showForm;
  }

  // TODO:
  // - Wystylować elementy
}
