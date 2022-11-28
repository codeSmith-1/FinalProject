import { DailyReport } from "./daily-report";
import { Mood } from "./mood";

export class MoodEntry {
  id: number;
  dailyReport: DailyReport;
  enteredAt: string | undefined;
  mood: Mood;

  constructor(id: number = 0, dailyReport: DailyReport, mood: Mood, enteredAt?: string){
    this.id = id;
    this.dailyReport = dailyReport;
    this.enteredAt = enteredAt;
    this.mood = mood;
  }


}
