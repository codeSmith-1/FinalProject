import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DailyReport } from 'src/app/models/daily-report';
import { ReportImage } from 'src/app/models/report-image';
import { AuthService } from 'src/app/services/auth.service';
import { DailyReportService } from 'src/app/services/daily-report.service';
import { ReportImageService } from 'src/app/services/report-image.service';

@Component({
  selector: 'app-view-daily-report',
  templateUrl: './view-daily-report.component.html',
  styleUrls: ['./view-daily-report.component.css']
})
export class ViewDailyReportComponent implements OnInit {

  selected: null | DailyReport = null;
  report: DailyReport | null = null;
  reports: DailyReport[] = [];
  tableView: boolean = true;
  reportView: boolean = false;
  images: ReportImage[] = [];


  constructor(private reportService: DailyReportService, private datePipe: DatePipe, private auth: AuthService, private imageService: ReportImageService) { }

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

  loggedIn(): boolean{
    return this.auth.checkLogin();
  }

  displayReport(report: DailyReport) {
    this.selected = report;
    this.tableView = false;
    this.reportView = true;
  }

  displayTable(){
    this.selected = null;
    this.tableView = true;
    this.reportView = false;
  }

}