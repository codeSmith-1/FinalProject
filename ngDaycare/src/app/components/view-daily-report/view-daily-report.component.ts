import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Bathroom } from 'src/app/models/bathroom';
import { DailyReport } from 'src/app/models/daily-report';
import { Food } from 'src/app/models/food';
// import { Mood } from 'src/app/models/mood';
import { MoodEntry } from 'src/app/models/mood-entry';
import { Nap } from 'src/app/models/nap';
import { ReportImage } from 'src/app/models/report-image';
import { AuthService } from 'src/app/services/auth.service';
import { BathroomService } from 'src/app/services/bathroom.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { FoodService } from 'src/app/services/food.service';
import { KidService } from 'src/app/services/kid.service';
import { NapService } from 'src/app/services/nap.service';
import { ReportImageService } from 'src/app/services/report-image.service';

@Component({
  selector: 'app-view-daily-report',
  templateUrl: './view-daily-report.component.html',
  styleUrls: ['./view-daily-report.component.css'],
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
  bathrooms: Bathroom[] = [];

  constructor(
    private currentRoute: ActivatedRoute,
    private foodService: FoodService,
    private bathroomService: BathroomService,
    private napService: NapService,
    private reportService: DailyReportService,
    private auth: AuthService,
    private imageService: ReportImageService,
    private kidService: KidService
  ) {}

  ngOnInit(): void {
    let kidId: string | number | null =
      this.currentRoute.snapshot.paramMap.get('kidId');
    if (kidId !== null) {
      kidId = parseInt(kidId);
      this.loadReports(kidId);
      this.displayTable();
    }
  }

  loadReports(kidId: number) {
    this.kidService.showReportsByKidId(kidId).subscribe({
      next: (reports) => {
        this.reports = reports;
      },
      error: (error) => {
        console.error('ViewDailyReport.loadReports: error loading reports');
        console.error(error);
      },
    });
  }

  showImagesByReport(selected: DailyReport) {
    this.imageService.showByReport(selected.id).subscribe({
      next: (images) => {
        this.images = images;
      },
      error: (error) => {
        console.error(
          'ShowImagesByReport.view-daily-report component: error loading images' +
            error
        );
      },
    });
  }

  showNapByReport(selected: DailyReport) {
    this.napService.showNapsByReport(selected.id).subscribe({
      next: (nap) => {
        this.nap = nap;
      },
      error: (error) => {
        console.error(
          'ShowNapByReport.view-daily-report component: error loading nap' +
            error
        );
      },
    });
  }

  showFoodByReport(selected: DailyReport) {
    this.foodService.showByReport(selected.id).subscribe({
      next: (food) => {
        this.food = food;
      },
      error: (error) => {
        console.error(
          'ShowFoodByReport.view-daily-report component: error loading food' +
            error
        );
      },
    });
  }

  showBathroomByReport(selected: DailyReport) {
    this.bathroomService.showBathroomByReport(selected.id).subscribe({
      next: (bathrooms) => {
        this.bathrooms = bathrooms;
      },
      error: (error) => {
        console.error(
          'ShowMoodByReport.view-daily-report component: error loading mood' +
            error
        );
      },
    });
  }
  showMoodByReport(selected: DailyReport) {
    this.reportService.showMoodByReport(selected.id).subscribe({
      next: (moods) => {
        this.moods = moods;
      },
      error: (error) => {
        console.error(
          'showMoodByReport().view-daily-report component: error loading moods' +
            error
        );
      },
    });
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  displayReport(report: DailyReport) {
    this.selected = report;
    this.tableView = false;
    this.reportView = true;
    this.updateView = false;
  }

  displayTable() {
    // this.selected = null;
    this.tableView = true;
    this.reportView = false;
    this.updateView = false;
  }
}
