import { Address } from "./address";
import { User } from "./user";

export class Adult {
  id: number;
  user: User | undefined;
  address: Address;
  firstName: string | undefined;
  lastName: string | undefined;
  phoneNumber: string | null;
  imageUrl: string | undefined;
  emergencyContact: Boolean | undefined;

  constructor(id: number=0, user: User = new User(), address: Address=new Address(), firstName: string="", lastName: string='', phoneNumber: string='', imageUrl: string='', emergencyContact: boolean = true){
    this.id = id;
    this.user = user;
    this.address = address;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.emergencyContact = emergencyContact;
  }
}
