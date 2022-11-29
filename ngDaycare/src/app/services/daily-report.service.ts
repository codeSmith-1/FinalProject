import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DailyReport } from '../models/daily-report';
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
