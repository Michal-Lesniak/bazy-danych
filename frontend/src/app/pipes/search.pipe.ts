import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(data: any, args?: any): any {
    if(!data) return null;
    if(!args) return data;

    args = args.toLowerCase();

    return data.filter((item: any) => {
      return JSON.stringify(item).toLowerCase().includes(args);
    })
  }

}
