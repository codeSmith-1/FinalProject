import { Time } from "@angular/common";
import { Timestamp } from "rxjs";
import { DailyReport } from "./daily-report";

export class Nap {
  id: number;
  timeStart: string | undefined;
  timeFinish: string | undefined;
  day: DailyReport;

  constructor(id: number = 0, day: DailyReport, timeStart?: string, timeFinish?: string){
    this.id = id;
    this.day = day;
    this.timeStart = timeStart;
    this.timeFinish = timeFinish;
  }
}
