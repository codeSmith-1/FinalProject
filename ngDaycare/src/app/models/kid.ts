import { Classroom } from "./classroom";
import { DailyReport } from "./daily-report";

export class Kid {
  id: number;
  firstName: string | undefined;
  lastName: string | undefined;
  birthday: Date | undefined;
  imageUrl: string | undefined;
  classroom: Classroom | null;
  dailyReport: DailyReport[] | null;

  constructor(id: number = 0, dailyReport: DailyReport[] = [], classroom: Classroom | null = null, firstName?: string, lastName?: string, birthday?: Date, imageUrl?: string){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.imageUrl = imageUrl;
    this.classroom = classroom;
    this.dailyReport = dailyReport;
  }
}
