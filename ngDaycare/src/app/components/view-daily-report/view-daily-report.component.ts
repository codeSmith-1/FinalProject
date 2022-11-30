import { Component, OnInit } from '@angular/core';
import { Bathroom } from 'src/app/models/bathroom';
import { DailyReport } from 'src/app/models/daily-report';
import { Food } from 'src/app/models/food';
import { Mood } from 'src/app/models/mood';
import { MoodEntry } from 'src/app/models/mood-entry';
import { Nap } from 'src/app/models/nap';
import { ReportImage } from 'src/app/models/report-image';
import { AuthService } from 'src/app/services/auth.service';
import { BathroomService } from 'src/app/services/bathroom.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { FoodService } from 'src/app/services/food.service';
import { NapService } from 'src/app/services/nap.service';
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
  nap: Nap = new Nap();
  bathrooms: Bathroom [] = []

  constructor(private foodService: FoodService, private bathroomService: BathroomService, private napService: NapService, private reportService: DailyReportService, private auth: AuthService, private imageService: ReportImageService) { }

  ngOnInit(): void {
    this.loadReports();
    this.displayTable();
  }

  loadReports() {
    this.reportService.index().subscribe({
      next: (reports) => {
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
        this.images = images;
      },
      error: (error) => {
        console.error('ShowImagesByReport.view-daily-report component: error loading images'+ error);
      },
    })
  }

  ShowNapByReport(selected: DailyReport){
    this.napService.showNapsByReport(selected.id).subscribe({
      next: (nap) => {
        this.nap = nap;
      },
      error: (error) => {
        console.error('ShowNapByReport.view-daily-report component: error loading nap'+ error);
      },
    })
  }

  ShowFoodByReport(selected: DailyReport){
    this.foodService.showByReport(selected.id).subscribe({
      next: (food) => {
        this.food = food;
      },
      error: (error) => {
        console.error('ShowFoodByReport.view-daily-report component: error loading food'+ error);
      },
    })
  }

  ShowBathroomByReport(selected: DailyReport){
    this.bathroomService.showBathroomByReport(selected.id).subscribe({
      next: (bathrooms) => {
        this.bathrooms = bathrooms;
      },
      error: (error) => {
        console.error('ShowMoodByReport.view-daily-report component: error loading mood'+ error);
      },
    })
  }
  ShowMoodByReport(selected: DailyReport){
    this.reportService.showMoodByReport(selected.id).subscribe({
      next: (moods) => {
        this.moods = moods;
      },
      error: (error) => {
        console.error('ShowBathroomByReport.view-daily-report component: error loading bathroom'+ error);
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
