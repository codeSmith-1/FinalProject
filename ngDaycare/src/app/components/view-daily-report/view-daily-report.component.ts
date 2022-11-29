import { Component, OnInit } from '@angular/core';
import { DailyReport } from 'src/app/models/daily-report';
import { AuthService } from 'src/app/services/auth.service';
import { DailyReportService } from 'src/app/services/daily-report.service';

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


  constructor(private reportService: DailyReportService, private auth: AuthService) { }

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

  // show(reportId: number){
  //   this.reportService.show(reportId).subscribe({
  //     next: (report) => {
  //       this.report = report;
  //     },
  //     error: (fail) => {
  //       console.error('ViewDailyReport.show: error loading report ' + fail)
  //     }
  //   })
  // }

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
