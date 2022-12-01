import { DailyReport } from "./daily-report";
import { Mood } from "./mood";

export class MoodEntry {

  enteredAt: string | undefined;
  dailyReport: DailyReport;
  mood: Mood;

  constructor(dailyReport: DailyReport = new DailyReport(), mood: Mood = new Mood(), enteredAt?: string){

    this.dailyReport = dailyReport;
    this.enteredAt = enteredAt;
    this.mood = mood;
  }


}
