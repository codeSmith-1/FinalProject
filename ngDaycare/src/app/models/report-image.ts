import { DailyReport } from "./daily-report";
import { Staff } from "./staff";

export class ReportImage {
  id: number;
  imageUrl: string | undefined;
  createdAt: string | undefined;
  dailyReport: DailyReport | null;
  staff: Staff | null;

  constructor(id: number = 0, dailyReport: DailyReport | null = null, staff: Staff | null = null, imageUrl: string = '', createdAt: string = ''){
    this.id = id;
    this.dailyReport = dailyReport;
    this.staff = staff;
    this.createdAt = createdAt;
    this.imageUrl = imageUrl;
  }
}
