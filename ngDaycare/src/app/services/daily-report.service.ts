import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DailyReport } from '../models/daily-report';
import { Mood } from '../models/mood';
import { MoodEntry } from '../models/mood-entry';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DailyReportService {

  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + 'api/reports';

  constructor(private http: HttpClient, private auth: AuthService) { }

  show(id: number): Observable<DailyReport> {
    return this.http.get<DailyReport>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DailyReport.index(): error retrieving report: ' + err)
        );
      })
    );
  }



  create(kidId: number): Observable<DailyReport>{
    console.log(kidId);
    return this.http.post<DailyReport>(this.url + '/' + kidId, new DailyReport(), this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DailyReport.create(): error creating report: ' + err)
        );
      })
    );
  }

  update(dailyReport: DailyReport): Observable<DailyReport>{
    return this.http.post<DailyReport>(this.url, dailyReport, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DailyReport.update(): error creating report: ' + err)
        );
      })
    );
  }

  index(): Observable<DailyReport[]> {
    return this.http.get<DailyReport[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DailyReport.index(): error retrieving reports: ' + err)
        );
      })
    );
  }

  showMoodByReport(reportId: number): Observable<MoodEntry[]> {
    return this.http.get<MoodEntry[]>(this.url + '/mood/' + reportId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('daily-report.service.showMoodByReport(): error retrieving mood: ' + err)
        );
      })
    );
  }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


}
