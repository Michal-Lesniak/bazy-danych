import { Pipe, PipeTransform } from '@angular/core';
import { RepairDetails } from '../interfaces/repair-details';

@Pipe({
  name: 'filterByCustomerName'
})
export class FilterByCustomerNamePipe implements PipeTransform {

  transform(data: RepairDetails[], searchText: string): RepairDetails[] {
    if(!data) return [];
    if(!searchText) return data;


    return data.filter(item => 
      item.customer.name
      .toLowerCase()
      .includes(searchText.toLowerCase())); 
  }
}
