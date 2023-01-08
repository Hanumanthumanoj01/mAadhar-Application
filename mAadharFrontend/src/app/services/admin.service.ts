import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

const getNewAadharDetails =
  'http://localhost:8093/AadharApp/admin/applications';
const approveNewAadharApplication =
  'http://localhost:8093/AadharApp/admin/applications/approvenewAadharApplication';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  //headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient: HttpClient, private router: Router) {}

  getNewAadharDetails(): Observable<any> {
    return this.httpClient.get(getNewAadharDetails, {
      withCredentials: true,
    });
  }

  approveNewAadharApplication(data: {
    applicationId: number;
  }): Observable<any> {
    return this.httpClient.put(approveNewAadharApplication, data, {
      withCredentials: true,
      responseType: 'text',
    });
  }
}
