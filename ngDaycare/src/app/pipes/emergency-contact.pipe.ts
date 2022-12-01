import { Pipe, PipeTransform } from '@angular/core';
import { Guardian } from '../models/guardian';

@Pipe({
  name: 'emergencyContact',
})
export class EmergencyContactPipe implements PipeTransform {

  transform(guardians: Guardian[] | null) {
    if (guardians) {
      let result: Guardian[] = [];
      for (let i = 0; i < guardians.length; i++) {
        if (guardians[i].adult?.emergencyContact) {
          result.push(guardians[i]);
        }
      }
      return result;
    }
    return null;
  }
}
