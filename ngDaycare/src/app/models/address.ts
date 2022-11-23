export class Address {
  id: number;
  street: string | undefined;
  city: string | undefined;
  state: string | undefined;
  zip: string | undefined;

  constructor(id: number =0, street: string='', city: string='', state: string='', zip: string=''){
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }
}
