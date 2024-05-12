import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, of, tap, throwError} from "rxjs";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient,
              private toast: ToastrService) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/login`, credentials);
  }

  saveToken(token: string): void {
    localStorage.setItem('auth_token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logOut(): Observable<any> {
    return of(localStorage.removeItem('auth_token')).pipe(
      tap(() => {
        this.toast.success('You have successfully logged out', 'Success');
      }),
      catchError(error => {
          console.error('You haven`t logged out yet', error);
          return throwError(error);
        }
      )
    )
  }

  getUsername(): string | null {
    const token = this.getToken();
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        const username = payload.sub;
        return username;
      } catch (error) {
        console.error('Error parsing token payload:', error);
        return null;
      }
    }
    return null;
  }

  getUserIdByUsername(username: string): Observable<number> {
    const token = this.getToken();
    if (!token) {
      return throwError('User not authenticated');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(`${this.baseUrl}/userByUsername/${username}`, { headers }).pipe(
      map(response => {
        if (response && response.id) {
          return response.id;
        } else {
          throw new Error('User not found');
        }
      }),
      catchError(error => {
        console.error('Error getting user ID by username:', error);
        return throwError(error);
      })
    );
  }
}
