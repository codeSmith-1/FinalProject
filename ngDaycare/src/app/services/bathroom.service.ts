import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bathroom } from '../models/bathroom';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BathroomService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + "api/bathrooms";
  constructor(private http: HttpClient, private auth: AuthService) { }

  index(): Observable<Bathroom[]> {
    // send http Options
    return this.http.get<Bathroom[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('BathroomService.index()'));
      })
    );
  }

  show(id: number): Observable<Bathroom> {
    return this.http.get<Bathroom>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BathroomService.index(): error retrieving bathroom: ' + err)
        );
      })
    );
  }

  create(bathroom: Bathroom): Observable<Bathroom> {
    return this.http
    .post<Bathroom>(this.url + 'bathroom', bathroom, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BathroomService.create(): error creating bathroom.')
        );
      })
    );
  }

  update(id: number, bathroom: Bathroom): Observable<Bathroom> {
    return this.http.put<Bathroom>(this.url + '/' + id, bathroom, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('bathroom.update(): error updating bathroom.'));
      })
    );
  }

  destroy(id: number): Observable<void> {
        // send http Options
    return this.http.delete<void>(this.url + '/' + id).pipe();
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

  showBathroomByReport(reportId: number): Observable<Bathroom[]> {
    return this.http.get<Bathroom[]>(this.url + '/' + reportId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BathroomService.showBathroomByReport: error retrieving bathrooms: ' + err)
        );
      })
    );
  }

}
