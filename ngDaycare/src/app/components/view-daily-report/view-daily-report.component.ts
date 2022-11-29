import { Component, OnInit } from '@angular/core';
import { DailyReport } from 'src/app/models/daily-report';
import { Food } from 'src/app/models/food';
import { Mood } from 'src/app/models/mood';
import { MoodEntry } from 'src/app/models/mood-entry';
import { ReportImage } from 'src/app/models/report-image';
import { AuthService } from 'src/app/services/auth.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { FoodService } from 'src/app/services/food.service';
import { ReportImageService } from 'src/app/services/report-image.service';

@Component({
  selector: 'app-view-daily-report',
  templateUrl: './view-daily-report.component.html',
  styleUrls: ['./view-daily-report.component.css']
})
export class ViewDailyReportComponent implements OnInit {


  selected: DailyReport | null = null;
  report: DailyReport | null = null;
  reports: DailyReport[] = [];
  tableView: boolean = true;
  reportView: boolean = false;
  updateView: boolean = false;
  images: ReportImage[] = [];
  food: Food[] = [];
  moods: MoodEntry[] = [];

  constructor(private foodService: FoodService, private reportService: DailyReportService, private auth: AuthService, private imageService: ReportImageService) { }

  ngOnInit(): void {
    this.loadReports();
    this.displayTable();
  }

  loadReports() {
    this.reportService.index().subscribe({
      next: (reports) => {
        console.log(reports);
        this.reports = reports;
      },
      error: (error) => {
        console.error('ViewDailyReport.loadReports: error loading reports');
        console.error(error);
      },
    });
  }

  ShowImagesByReport(selected: DailyReport){
    this.imageService.showByReport(selected.id).subscribe({
      next: (images) => {
        console.log(selected.id);
        this.images = images;
      },
      error: (error) => {
        console.error('ShowImagesByReport.view-daily-report component: error loading images'+ error);
      },
    })
  }

  ShowFoodByReport(selected: DailyReport){
    this.foodService.showByReport(selected.id).subscribe({
      next: (food) => {
        console.log(selected.id);
        this.food = food;
      },
      error: (error) => {
        console.error('ShowFoodByReport.view-daily-report component: error loading food'+ error);
      },
    })
  }

  ShowMoodByReport(selected: DailyReport){
    this.reportService.showMoodByReport(selected.id).subscribe({
      next: (moods) => {
        console.log(selected.id);
        this.moods = moods;
      },
      error: (error) => {
        console.error('ShowMoodByReport.view-daily-report component: error loading mood'+ error);
      },
    })
  }

  loggedIn(): boolean{
    return this.auth.checkLogin();
  }

  displayReport(report: DailyReport) {
    this.selected = report;
    this.tableView = false;
    this.reportView = true;
    this.updateView = false;
  }

  displayTable(){
    // this.selected = null;
    this.tableView = true;
    this.reportView = false;
    this.updateView = false;
  }

}
