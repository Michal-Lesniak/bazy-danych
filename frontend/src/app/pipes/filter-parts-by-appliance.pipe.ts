import { Pipe, PipeTransform } from '@angular/core';
import { Part } from '../interfaces/part';

@Pipe({
  name: 'filterPartsByAppliance'
})
export class FilterPartsByAppliancePipe implements PipeTransform {

  transform(data: Part[], arg: string | null | undefined): Part[] {
    if(arg === null || arg === undefined) return data;
    if(!data) return [];

    return data.filter(item => item.applianceCode ===  Number(arg) );
  }

}
