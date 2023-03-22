import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../interfaces/customer';

@Injectable()
export class ConnectionService {

  constructor(private http: HttpClient) { }

  public checkConnection() {
    return this.http.get<boolean>('http://localhost:8080');
  }

  /****************** CUSTOMERS ********************/
  public getCustomers() {
    return this.http.get<Customer[]>('http://localhost:8080/customers');
  }

  public addCustomer(body: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    })
  
    return this.http.post<Customer>('http://localhost:8080/customers', JSON.stringify(body), { headers: headers });
  }

  public deleteCustomer(userCode: string) {
    return this.http.delete<boolean>(`http://localhost:8080/customers/${userCode}`);
  }
}


