import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import { environment } from 'src/environments/environment';
import { Adult } from '../models/adult';

@Injectable({
  providedIn: 'root',
})
export class AdultService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl;
  constructor(private http: HttpClient) {}

  // create adult
  // update adult

  create(adult: Adult): Observable<Adult> {
    // Create POST request to register a new account
    return this.http.post<Adult>(this.url + 'adults', adult).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdultService.create(): error creating adult.')
        );
      })
    );
  }
}
