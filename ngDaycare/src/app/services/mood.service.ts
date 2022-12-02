import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Mood } from '../models/mood';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MoodService {

  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + 'api/reports/moods';

  constructor(private http: HttpClient, private auth: AuthService) { }

  show(): Observable<Mood[]> {
    return this.http.get<Mood[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DailyReport.index(): error retrieving report: ' + err)
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
