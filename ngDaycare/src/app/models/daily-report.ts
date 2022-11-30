import { Food } from "./food";
import { Kid } from "./kid";
import { MoodEntry } from "./mood-entry";
import { Nap } from "./nap";
import { ReportImage } from "./report-image";

export class DailyReport {
  id: number;
  nap: Nap;
  timeIn: string;
  timeOut: string;
  diapersLow: boolean;
  wipesLow: boolean;
  activities: string | undefined;
  notes: string | undefined;
  reportDate: string | undefined;
  images: ReportImage[] | undefined;
  moodEntry: MoodEntry;
  food: Food;
  kid: Kid;



  constructor(id: number = 0, nap: Nap = new Nap(), kid: Kid = new Kid(), moodEntry: MoodEntry = new MoodEntry(), food: Food = new Food(), timeIn: string = "", timeOut: string = "", diapersLow: boolean = false, wipesLow: boolean = false, activities?: string, notes?: string, reportDate?:string, images?: ReportImage[] ){
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
    this.food = food;
    this.kid = kid;
    this.nap = nap;
  }

}
