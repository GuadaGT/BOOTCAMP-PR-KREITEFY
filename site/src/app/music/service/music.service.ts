import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {AuthService} from "../../auth/service/auth.service";
import {SongSimple} from "../../model/SongSimple.inteface";
import {Song} from "../../model/Song.interface";
import {UserSong} from "../../model/UserSong.interface";
import {Reproductions} from "../../model/Reproductions.interface";

@Injectable({
  providedIn: 'root'
})
export class MusicService {
  private baseUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient,
              private authService: AuthService) {}

  getToken(): string | null {
    return this.authService.getToken();
  }

  getRecentSongs(currentPage: number, pageSize: number, filtersRecent?: { [key: string]: string }): Observable<any> {
    let recentSongsURI = `${this.baseUrl}songs/new?page=${currentPage}&size=${pageSize}`;
    if (filtersRecent) {
      const filterParams = Object.entries(filtersRecent)
        .map(([key, value]) => `${key}:EQUAL:${value}`)
        .join(',');
      recentSongsURI += `&filter=${filterParams}`;
    }
    const token = this.getToken();
    if (!token) {
      return new Observable<SongSimple[]>(observer => observer.error('User not authenticated'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(recentSongsURI, { headers });
  }

  getAllSongs(currentPage: number, pageSize: number, filters?: { [key: string]: string }): Observable<any> {
    let allSongsURI = `${this.baseUrl}allsongs?page=${currentPage}&size=${pageSize}`;
    if (filters) {
      const filterParams = Object.entries(filters)
        .map(([key, value]) => `${key}:EQUAL:${value}`)
        .join(',');
      allSongsURI += `&filter=${filterParams}`;
    }
    const token = this.getToken();
    if (!token) {
      return new Observable<SongSimple[]>(observer => observer.error('User not authenticated'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(allSongsURI, { headers });
  }

  getSong(songId: number): Observable<Song> {
    const token = this.getToken();
    if (!token) {
      return new Observable<Song>(observer => observer.error('User not authenticated'));
    }
    const songURI = `${this.baseUrl}songs/${songId}`;

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Song>(songURI, { headers });
  }

  getStyles(page: number, pageSize: number, filter?: string): Observable<string[]> {
    let stylesURI = `${this.baseUrl}styles?page=${page}&size=${pageSize}`;
    if (filter) {
      stylesURI += `&style=${filter}`;
    }
    const token = this.getToken();
    if (!token) {
      return new Observable<string[]>(observer => observer.error('User not authenticated'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<string[]>(stylesURI, { headers });
  }

  addReproductions(userSong: UserSong): Observable<UserSong> {
    const token = this.getToken();
    if (!token) {
      return new Observable<UserSong>(observer => observer.error('User not authenticated'));
    }
    userSong.reproductionDate = new Date();
    const songReproductionsURI = `${this.baseUrl}song/${userSong.songId}/user/${userSong.userId}/reproductions`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<UserSong>(songReproductionsURI, userSong, { headers });
  }

  addRating(userSong: UserSong, rating: number): Observable<UserSong> {
    if (rating < 1 || rating > 4) {
      return new Observable<UserSong>(observer => observer.error('Rating must be between 1 and 4'));
    }
    const token = this.getToken();
    if (!token) {
      return new Observable<UserSong>(observer => observer.error('User not authenticated'));
    }
    const songRatingURI = `${this.baseUrl}song/${userSong.songId}/user/${userSong.userId}/rating?rating=${rating}`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.put<UserSong>(songRatingURI, userSong, { headers });
  }

  getSongsByReproductions(currentPage: number, pageSize: number, filterReproductions?: { [key: string]: string }): Observable<any>{
    let reproductionsSongsUri = `${this.baseUrl}songs/reproductions?page=${currentPage}&size=${pageSize}`;
    if (filterReproductions) {
      const filterParams = Object.entries(filterReproductions)
        .map(([key, value]) => `${key}:EQUAL:${value}`)
        .join(',');
      reproductionsSongsUri += `&filter=${filterParams}`;
    }
    const token = this.getToken();
    if (!token) {
      return new Observable<SongSimple[]>(observer => observer.error('User not authenticated'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(reproductionsSongsUri, { headers });
  }

  getFavoriteSongs(userId: number, currentPage: number, pageSize: number): Observable<any> {
    const favoritesSongsUri = `${this.baseUrl}user/${userId}/ratings?page=${currentPage}&size=${pageSize}`;
    const token = this.getToken();
    if (!token) {
      return throwError(new Error('User not authenticated'));
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(favoritesSongsUri, { headers }).pipe(
      catchError(error => throwError(error))
    );
  }

  getReproductionsCount(userId: number): Observable<number> {
    const reproductionsCountUrl = `${this.baseUrl}user/${userId}/reproductions/count`;
    const token = this.getToken();
    if (!token) {
      return throwError('User not authenticated');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<number>(reproductionsCountUrl, { headers }).pipe(
      catchError(error => throwError('Error retrieving reproductions count'))
    );
  }

  getReproductions(userId: number, page: number, size: number): Observable<Reproductions[]> {
    const reproductionsUrl = `${this.baseUrl}user/${userId}/reproductions?page=${page}&size=${size}`;
    const token = this.getToken();
    if (!token) {
      return throwError('User not authenticated');
    }
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Reproductions[]>(reproductionsUrl, { headers }).pipe(
      catchError(error => throwError('Error retrieving reproductions'))
    );
  }
}
