import { first } from "rxjs";

export class Adult {
  id: number;
  firstName: string | undefined;
  lastName: string | undefined;
  phoneNumber: number | null;
  imageUrl: string | undefined;
  emergencyContact: Boolean | undefined;

  constructor(id: number=0, firstName: string="", lastName: string='', phoneNumber: number=0, imageUrl: string='', emergencyContact: boolean = true){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.emergencyContact = emergencyContact;
  }
}
