import { first } from "rxjs";
import { Address } from "./address";
import { User } from "./user";

export class Adult {
  id: number;
  firstName: string | undefined;
  lastName: string | undefined;
  phoneNumber: string | null;
  imageUrl: string | undefined;
  emergencyContact: Boolean | undefined;
  user: User | undefined;
  address: Address | undefined;

  constructor(id: number=0, firstName: string="", lastName: string='', phoneNumber: string='', imageUrl: string='', emergencyContact: boolean = true, user?: User, address?: Address){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.emergencyContact = emergencyContact;
    this.user = user;
  }
}
