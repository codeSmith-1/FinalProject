import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'accountRequests'
})
export class AccountRequestsPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
