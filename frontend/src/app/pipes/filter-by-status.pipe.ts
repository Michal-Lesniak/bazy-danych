import { Pipe, PipeTransform } from '@angular/core';
import { RepairDetails } from '../interfaces/repair-details';

@Pipe({
  name: 'filterByStatus'
})
export class FilterByStatusPipe implements PipeTransform {

  transform(data: RepairDetails[], status: boolean ): RepairDetails[] {
    if(!data) return [];
    if(status === null || status === undefined) return data;
    

    return data.filter(item => item.status === status);
  }

}
