import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BathroomType } from '../models/bathroom-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class BathroomTypeService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + "api/bathroomTypes";
  constructor(private http: HttpClient, private auth: AuthService) { }

  showBathroomTypes(): Observable<BathroomType[]> {
    return this.http.get<BathroomType[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('BathroomService.index()'));
      })
    );
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
