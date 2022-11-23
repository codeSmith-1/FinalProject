import { User } from "./user";

export class Staff {
  id: number;
  firstName: string | undefined;
  lastName: string | undefined;
  imageUrl: string | undefined;
  classroom: string | undefined;
  user: User | undefined;

  constructor(id: number = 0, firstName?: string, lastName?: string, imageUrl?: string, classroom?: string, user?: User){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.imageUrl = imageUrl;
    this.classroom = classroom;
    this.user = user;
  }
}
