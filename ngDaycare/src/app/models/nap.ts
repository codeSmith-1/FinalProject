import { Time } from "@angular/common";
import { Timestamp } from "rxjs";
import { DailyReport } from "./daily-report";

export class Nap {
  id: number;
  day: DailyReport | null;
  timeStart: string | undefined;
  timeFinish: string | undefined;

  constructor(id: number = 0, day: DailyReport | null = null, timeStart: string = '', timeFinish: string = ''){
    this.id = id;
    this.day = day;
    this.timeStart = timeStart;
    this.timeFinish = timeFinish;
  }
}
