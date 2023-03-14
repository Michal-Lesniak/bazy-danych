import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConnectionService } from '../services/connection.service';
import { Customer } from '../interfaces/customer';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  public customersArray: Customer[] = []; 
  public errorMessage = false;

  constructor(private connection: ConnectionService) { }

  ngOnInit() {
    this.getUsers()
  }

  public customerForm = new FormGroup({
    userCode: new FormControl('', [Validators.required, Validators.pattern(/^\d+$/)]),
    name: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    phone: new FormControl('', Validators.required)
  });

  public getUsers() {
    this.connection.getCustomers().subscribe(data => {
      this.customersArray = data;
    })
  }

  public addUser() {
    const formData = {...this.customerForm.value};
    this.connection.addCustomer(formData).subscribe(data => {
      this.getUsers();
      this.customerForm.reset()
    }, () => {
      this.errorMessage = true;
      setTimeout(() => this.errorMessage = false, 5000);
    })
  }

  // TODO:
  // - Wystylować elementy
}
