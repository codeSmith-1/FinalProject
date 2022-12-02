import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Adult } from '../models/adult';
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

  show(): Observable<Staff>{
    return this.http.get<Staff>(this.url +"/loggedInStaff", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StaffSvc.show(): error retrieving Staff: ' + err)
        );
      })
    );
  }

  update(staff: Staff){
    return this.http.put<Staff>(this.url, staff, this.getHttpOptions()).pipe(
      catchError((err: any)=> {
        console.error(err);
        return throwError(
          () => new Error('StaffSvc.update(): error updating Staff: ' + err)
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

  index(): Observable<Staff[]>{
    return this.http.get<Staff[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StaffService.index(): error retrieving staff: ' + err)
        );
      })
    );
  }

  indexClassroom(): Observable<Classroom[]>{
    return this.http.get<Classroom[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('indexClassroom.index(): error retrieving classrooms: ' + err)
        );
      })
    );
  }

  getAdults(): Observable<Adult[]>{
    return this.http.get<Adult[]>(this.url +'/adults', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('StaffService.index(): error retrieving adults: ' + err)
        );
      })
    );
  }

}
