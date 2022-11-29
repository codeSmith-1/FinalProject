import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Bathroom } from '../models/bathroom';
import { Kid } from '../models/kid';

@Injectable({
  providedIn: 'root'
})
export class BathroomService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl;
  constructor(private http: HttpClient) { }

  index(): Observable<Bathroom[]> {
    return this.http.get<Bathroom[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('BathroomService.index()'));
      })
    );
  }

  show(id: number): Observable<Bathroom> {
    return this.http.get<Bathroom>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BathroomService.index(): error retrieving bathroom: ' + err)
        );
      })
    );
  }

  create(bathroom: Bathroom): Observable<Bathroom> {
    return this.http.post<Bathroom>(this.url + 'bathroom', bathroom).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BathroomService.create(): error creating bathroom.')
        );
      })
    );
  }

  update(id: number, bathroom: Bathroom): Observable<Bathroom> {
    return this.http.put<Bathroom>(this.url + '/' + id, bathroom).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('bathroom.update(): error updating bathroom.'));
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id).pipe();
  }
}
