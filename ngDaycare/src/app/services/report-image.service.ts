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
    return this.http.get<ReportImage[]>(this.url + '/reportId/' + reportId, this.getHttpOptions()).pipe(
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

  create(reportImage: ReportImage, reportId: number, staffId: number): Observable<ReportImage> {
    return this.http
    .post<ReportImage>(this.url + `/reportImage/reportId/${reportId}/staffId/${staffId}`, reportImage, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('report-image.service.create(): error creating report-image.')
        );
      })
    );
  }


  destroy(imageId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/reportImage/imageId/' + imageId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('editDailyReport.delete(): error deleting image: ' + err)
        );
      })
    );
  }

}
