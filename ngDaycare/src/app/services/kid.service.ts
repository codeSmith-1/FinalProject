import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Kid } from '../models/kid';

@Injectable({
  providedIn: 'root',
})
export class KidService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl;
  constructor(private http: HttpClient) {}

  index(): Observable<Kid[]> {
    return this.http.get<Kid[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('KidService.index()'));
      })
    );
  }

  show(id: number): Observable<Kid> {
    return this.http.get<Kid>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('KidService.index(): error retrieving kid: ' + err)
        );
      })
    );
  }

  create(kid: Kid): Observable<Kid> {
    return this.http.post<Kid>(this.url + 'kid', kid).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('KidService.create(): error creating kid.')
        );
      })
    );
  }

  update(id: number, kid: Kid): Observable<Kid> {
    return this.http.put<Kid>(this.url + '/' + id, kid).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('kid.update(): error updating kid.'));
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id).pipe();
  }
}
