import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  url = environment.baseUrl + 'api/users';
  newUser: User = new User();
  constructor(
    private http: HttpClient,
    private auth: AuthService
    ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.index(): error retrieving users: ' + err)
        );
      })
    );
  }

  show(todoId: number): Observable<User> {
    return this.http.get<User>(this.url + '/' + todoId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.show(): error retrieving user: ' + err)
        );
      })
    );
  }

  create(newUser: User) {
    this.newUser.role = '';
    this.newUser.enabled = false;
    return this.http.post<User>(this.url, newUser, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.create(): error creating users: ' + err)
        );
      })
    );
  }

  update(user: User): Observable<User> {
    return this.http.put<User>(this.url + '/' + user.id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.update(): error updating user: ' + err)
        );
      })
    );
  }

  destroy(userId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + userId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.delete(): error deleting user: ' + err)
        );
      })
    );
  }
}
