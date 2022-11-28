import { DailyReport } from "./daily-report";
import { Staff } from "./staff";

export class ReportImage {
  id: number;
  imageUrl: string | undefined;
  createdAt: string | undefined;
  dailyReport: DailyReport;
  staff: Staff;

  constructor(id: number = 0, dailyReport: DailyReport, staff: Staff, imageUrl?: string, createdAt?: string){
    this.id = id;
    this.dailyReport = dailyReport;
    this.staff = staff;
    this.createdAt = createdAt;
    this.imageUrl = imageUrl;
  }
}
