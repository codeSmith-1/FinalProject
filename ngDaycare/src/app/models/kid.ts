import { Classroom } from "./classroom";

export class Kid {
  id: number;
  classroom: Classroom;
  firstName: string | undefined;
  lastName: string | undefined;
  birthday: Date | undefined;
  imageUrl: string | undefined;

  constructor(id: number = 0, classroom: Classroom = new Classroom(), firstName?: string, lastName?: string, birthday?: Date, imageUrl?: string){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.imageUrl = imageUrl;
    this.classroom = classroom;
  }
}
