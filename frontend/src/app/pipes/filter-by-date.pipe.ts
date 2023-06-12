import { Pipe, PipeTransform } from '@angular/core';
import { RepairDetails } from '../interfaces/repair-details';

@Pipe({
  name: 'filterByDate'
})
export class FilterByDatePipe implements PipeTransform {

  transform(items: RepairDetails[], date: Date | null): RepairDetails[] {
    if (!items || !date) {
      return items;
    }
    return items.filter(item =>
      new Date(item.dateActionDtoList
        .reduce((latest, action) => 
        latest.date > action.date ? latest : action)?.date)
        .toDateString() === date.toDateString());
  }
}


