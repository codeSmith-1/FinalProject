import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Message } from '../models/message';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + 'api/messages';
  constructor(private http: HttpClient, private auth: AuthService) { }

  index():Observable<Message[]> {
    return this.http.get<Message[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('MessageService.index()'));
      })
    );
  }

  create(message: Message): Observable<Message> {
    return this.http.post<Message>(this.url + 'message', this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError(() => new Error('MessageSevice.create(): error creating message.' + err)
        )
      })
    )
    }


  showByRecipient(username : string): Observable<Message[]> {
    return this.http.get<Message[]>(this.url + '/' + username, this.getHttpOptions()).pipe(
      catchError((err: any)=> {
        console.log(err);
        return throwError(
          () => new Error('Message.show(): error retrieving message: ' + err)
        )
      })
    )
  }

  destroy(id:number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions()).pipe();
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
