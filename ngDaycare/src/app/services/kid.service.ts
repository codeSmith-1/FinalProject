import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Kid } from '../models/kid';
import { AuthService } from './auth.service';
import { buffer } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class KidService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl +"api/kids";

  constructor(private http: HttpClient, private auth: AuthService) {}

  index(): Observable<Kid[]> {
    return this.http.get<Kid[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('KidService.index()'));
      })
    );
  }

  show(id: number): Observable<Kid> {
    return this.http.get<Kid>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('KidService.index(): error retrieving kid: ' + err)
        );
      })
    );
  }

  create(kid: Kid): Observable<Kid> {
    return this.http
      .post<Kid>(this.url + 'kid', kid, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('KidService.create(): error creating kid.')
          );
        })
      );
  }

  update(kid: Kid): Observable<Kid> {
    return this.http
    .put<Kid>(this.url, kid, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('kid.update(): error updating kid.'));
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http
      .delete<void>(this.url + '/' + id, this.getHttpOptions())
      .pipe();
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
