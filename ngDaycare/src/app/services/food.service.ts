import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Food } from '../models/food';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private baseUrl = 'http://localhost:8089/';
  url = environment.baseUrl;
  constructor(private http: HttpClient) { }

index():Observable<Food[]> {
  return this.http.get<Food[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(() => new Error('FoodService.index()'));
    })
  );
}

create(food: Food): Observable<Food> {
  return this.http.post<Food>(this.url + 'food', food).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('FoodService.create(): error creating food.')
      );
    })
  );
}

update(id: number, food: Food): Observable<Food> {
  return this.http.put<Food>(this.url + '/' + id, food).pipe(
    catchError((err: any) => {
      console.error(err);
      return throwError(() => new Error('food.update(): error updating food.'));
    })
  );
}

destroy(id: number): Observable<void> {
  return this.http.delete<void>(this.url + '/' + id).pipe();
}

}
