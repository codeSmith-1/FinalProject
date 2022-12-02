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
  url = environment.baseUrl +'api/reports';

  constructor(private http: HttpClient, private auth: AuthService) { }

  showNapsByReport(reportId: number): Observable<Nap> {
    return this.http.get<Nap>(this.url + '/' + reportId + '/naps', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('NapService.showNapsByReport: error retrieving naps: ' + err)
        );
      })
    );
  }

  create(nap: Nap, reportId: number): Observable<Nap> {
    return this.http
    .post<Nap>(this.url + '/' + reportId + '/naps', nap, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('NapSvc.create(): error creating nap.')
        );
      })
    );
  }

  destroy(napId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/naps/' + napId, this.getHttpOptions()).pipe(
      catchError((err: any)=>{
        console.error(err);
        return throwError(() => new Error ('napSvc.destroy(): error deleting nap'));
      })
    );
  }

  update(nap: Nap): Observable<Nap> {
    return this.http.put<Nap>(this.url + '/naps', nap, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('NapSvc.update(): error updating nap.'));
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
