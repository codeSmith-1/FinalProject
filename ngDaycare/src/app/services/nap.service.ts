import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Nap } from '../models/nap';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class NapService {

  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl +'api/';

  constructor(private http: HttpClient, private auth: AuthService) { }

  showNapsByReport(reportId: number): Observable<Nap> {
    return this.http.get<Nap>(this.url + 'reports/' + reportId + '/naps', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('NapService.showNapsByReport: error retrieving naps: ' + err)
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
