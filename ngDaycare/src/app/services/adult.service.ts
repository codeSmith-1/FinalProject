import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import { environment } from 'src/environments/environment';
import { Adult } from '../models/adult';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AdultService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + "api/adults";
  constructor(private http: HttpClient, private auth: AuthService) {}

  // create adult
  // update adult

  create(adult: Adult, newUser: User): Observable<Adult> {
    // Create POST request to register a new account
    let credentials = this.auth.generateBasicAuthCredentials(
      newUser.username,
      newUser.password
    );
    let httpOptions = { headers: { Authorization: `Basic ${credentials}`}};
    console.log(httpOptions);
    return this.http.post<Adult>(this.url, adult, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AdultService.create(): error creating adult.')
        );
      })
    );
  }
}
