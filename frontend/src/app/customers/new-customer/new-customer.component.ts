import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.scss']
})
export class NewCustomerComponent {
  public customerForm = new FormGroup({
    userCode: new FormControl("", [
      Validators.required,
      Validators.pattern(/^\d+$/),
    ]),
    name: new FormControl("", Validators.required),
    email: new FormControl("", Validators.email),
    phone: new FormControl("", Validators.required),
  });

}

