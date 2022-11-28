import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Classroom } from '../models/classroom';
import { Staff } from '../models/staff';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class StaffService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + "api/staff";

  constructor(private http: HttpClient, private auth: AuthService) { }
  // create
  // update
  // read

  create(staff: Staff, newUser: User): Observable<Staff> {
    // Create POST request to register a new account
    let credentials = this.auth.generateBasicAuthCredentials(
      newUser.username,
      newUser.password
    );
    let httpOptions = { headers: { Authorization: `Basic ${credentials}`}};
    console.log(httpOptions);
    return this.http.post<Staff>(this.url, staff, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StaffService.create(): error creating staff.')
        );
      })
    );
  }




  // need a get for classrooms to display classrooms for staff to connect to when registering or updating.
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }
  index(): Observable<Classroom[]>{
    return this.http.get<Classroom[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error retrieving todos: ' + err)
        );
      })
    );
  }

}
