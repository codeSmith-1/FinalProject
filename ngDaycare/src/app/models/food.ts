import { DailyReport } from "./daily-report";

export class Food {
  id: number;
  lunch: string | undefined;
  amSnack: string | undefined;
  pmSnack: string | undefined;
  other: string | undefined;
  day: DailyReport;

  constructor(id: number = 0, day: DailyReport, lunch?: string, amSnack?: string, pmSnack?: string, other?: string){
    this.id = id;
    this.lunch = lunch;
    this.amSnack = amSnack;
    this.pmSnack = pmSnack;
    this.other = other;
    this.day = day;
  }
}
