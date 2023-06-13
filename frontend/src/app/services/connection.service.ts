import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../interfaces/customer';
import { Appliance } from '../interfaces/appliance';
import { Part } from '../interfaces/part';
import { Repair } from '../interfaces/repair';
import { RepairDetails } from '../interfaces/repair-details';
import { RepairStatus } from '../interfaces/repair-status';
import { DateAction } from '../interfaces/dateAction';
import { addRepair } from '../interfaces/addRepair';

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

  public getFreeCustomerCode(){
    return this.http.get<number>('http://localhost:8080/customers/freeCode');
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


  /****************** APPLIANCES ********************/
  public getAppliances() {
    return this.http.get<Appliance[]>('http://localhost:8080/appliances');
  }

  public getFreeApplianceCode(){
    return this.http.get<number>('http://localhost:8080/appliances/freeCode');
  }

  public addAppliance(body: any) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Accept': 'application/json'
    });

    return this.http.post<Appliance>('http://localhost:8080/appliances', JSON.stringify(body), { headers: headers });
  }

  public deleteAppliance(applianceCode: string) {
    return this.http.delete<boolean>(`http://localhost:8080/appliances/${applianceCode}`);
  }

  /****************** PARTS ********************/
  public getParts() {
    return this.http.get<Part[]>('http://localhost:8080/parts');
  }

  public getFreePartCode(){
    return this.http.get<number>('http://localhost:8080/parts/freeCode');
  }

  public addPart(body: any) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Accept': 'application/json'
    });

    return this.http.post<Part>('http://localhost:8080/parts', JSON.stringify(body), { headers: headers });
  }

  public deletePart(partCode: string) {
    return this.http.delete<boolean>(`http://localhost:8080/parts/${partCode}`);
  }


  /****************** REPAIRS ********************/

  public getRepairs() {
    return this.http.get<Repair[]>('http://localhost:8080/repairs');
  }

  public getFreeRepairCode(){
    return this.http.get<number>('http://localhost:8080/repairs/freeCode');
  }

  public getRepairsDetails() {
    return this.http.get<RepairDetails[]>('http://localhost:8080/repairsDetails');
  }

  public updateStatus(repairToUpdateStatus: RepairStatus) {
    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Accept': 'application/json'
    })

    return this.http.put<Boolean>('http://localhost:8080/repairs/status',
      JSON.stringify(repairToUpdateStatus),
      { headers: headers });
  }

  public addRepair(repair: addRepair) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    })

    return this.http.post<Repair>('http://localhost:8080/repairs',
      JSON.stringify(repair),
      { headers: headers });
  }

   /****************** DateAction ********************/

  public addDateActionToRepair(dateAction:any){
     const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
     })

     return this.http.post<DateAction>
                (`http://localhost:8080/repairs/${dateAction.repairCode}/date`,
                JSON.stringify(dateAction),
                {headers: headers })

    }

    public getFreeDateCode(){
      return this.http.get<number>('http://localhost:8080/dates/freeCode');
    }
 
}


