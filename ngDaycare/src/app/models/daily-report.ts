import { MoodEntry } from "./mood-entry";
import { ReportImage } from "./report-image";

export class DailyReport {
  id: number;
  timeIn: string | undefined;
  timeOut: string | undefined;
  diapersLow: boolean;
  wipesLow: boolean;
  activities: string | undefined;
  notes: string | undefined;
  reportDate: string | undefined;
  images: ReportImage[] | undefined;
  moodEntry: MoodEntry;

  constructor(id: number = 0, moodEntry: MoodEntry, timeIn: string, timeOut?: string, diapersLow: boolean = false, wipesLow: boolean = false, activities?: string, notes?: string, reportDate?:string, images?: ReportImage[] ){
    this.id = id;
    this.timeIn = timeIn;
    this.timeOut = timeOut;
    this.diapersLow = diapersLow;
    this.wipesLow = wipesLow;
    this.activities = activities;
    this.notes = notes;
    this.reportDate = reportDate;
    this.images = images;
    this.moodEntry = moodEntry;
  }
}
