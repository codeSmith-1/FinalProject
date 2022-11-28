import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Staff } from '../models/staff';

@Injectable({
  providedIn: 'root'
})
export class StaffService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl;

  constructor(private http: HttpClient) { }
  // create
  // update
  // read

  create(staff: Staff): Observable<Staff> {
    // Create POST request to register a new account
    return this.http.post<Staff>(this.url + 'staff', staff).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StaffService.create(): error created staff.')
        );
      })
    );
  }
}
