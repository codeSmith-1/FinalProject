import { DailyReport } from "./daily-report";
import { Mood } from "./mood";

export class MoodEntry {
  id: number;
  enteredAt: string | undefined;
  dailyReport: DailyReport;
  mood: Mood;

  constructor(id: number = 0, dailyReport: DailyReport = new DailyReport(), mood: Mood = new Mood(), enteredAt?: string){
    this.id = id;
    this.dailyReport = dailyReport;
    this.enteredAt = enteredAt;
    this.mood = mood;
  }


}
