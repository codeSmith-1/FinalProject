import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Food } from '../models/food';
import { FoodType } from '../models/food-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl + 'api/foods';
  constructor(private http: HttpClient, private auth: AuthService) { }

index():Observable<Food[]> {
  return this.http.get<Food[]>(this.url, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(() => new Error('FoodService.index()'));
    })
  );
}
listFoodType():Observable<FoodType[]> {
  return this.http.get<FoodType[]>(this.url + '/foodTypes', this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(() => new Error('FoodService.listFoodTypes()' + err));
    })
  );
}

create(food: Food, reportId: number): Observable<Food> {
  return this.http.post<Food>(this.url + '/' + reportId, food, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('FoodService.create(): error creating food.')
      );
    })
  );
}

showByReport(reportId: number): Observable<Food[]> {
  return this.http.get<Food[]>(this.url + '/' + reportId, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('Food.show(): error retrieving food: ' + err)
      );
    })
  );
}


update(id: number, food: Food): Observable<Food> {
  return this.http.put<Food>(this.url + '/' + id, food, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.error(err);
      return throwError(() => new Error('food.update(): error updating food.'));
    })
  );
}

destroy(id: number): Observable<void> {
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
