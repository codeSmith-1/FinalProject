import { BathroomType } from "./bathroom-type";
import { DailyReport } from "./daily-report";
import { Staff } from "./staff";

export class Bathroom {
  id: number;
  description: string | undefined;
  bathroomTime: string | undefined;
  type: BathroomType;
  day: DailyReport;
  staff: Staff;

  constructor(id: number = 0, type: BathroomType = new BathroomType(), day: DailyReport = new DailyReport(), staff: Staff = new Staff(), description?: string, bathroomTime?: string){
    this.id = id;
    this.bathroomTime = bathroomTime;
    this.type = type;
    this.day = day;
    this.staff = staff;
  }
}
