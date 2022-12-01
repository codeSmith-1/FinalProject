import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReportImage } from '../models/report-image';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReportImageService {
  url = environment.baseUrl + 'api/images';

  constructor(private auth: AuthService, private http: HttpClient) { }

  index(): Observable<ReportImage[]> {
    return this.http.get<ReportImage[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ReportImage.index(): error retrieving images: ' + err)
        );
      })
    );
  }

  showByReport(reportId: number): Observable<ReportImage[]> {
    return this.http.get<ReportImage[]>(this.url + '/' + reportId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ReportImage.show(): error retrieving image: ' + err)
        );
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

  create(reportImage: ReportImage): Observable<ReportImage> {
    return this.http
    .post<ReportImage>(this.url + 'reportImage', reportImage, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('report-image.service.create(): error creating report-image.')
        );
      })
    );
  }

  update(id: number, reportImage: ReportImage): Observable<ReportImage> {
    return this.http.put<ReportImage>(this.url + '/' + id, reportImage, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('report-image.update(): error updating report-image.'));
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id).pipe();
  }

}
