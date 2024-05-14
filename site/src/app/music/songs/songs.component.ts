import {Component, OnInit} from '@angular/core';
import { MusicService } from '../service/music.service';
import {SongSimple} from "../../model/SongSimple.inteface";
import {UserSong} from "../../model/UserSong.interface";
import {AuthService} from "../../auth/service/auth.service";
import {catchError, EMPTY, forkJoin, switchMap} from "rxjs";
import {Song} from "../../model/Song.interface";

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.scss']
})
export class SongsComponent implements OnInit {

  isLoggedIn:boolean = true;

  songs: SongSimple[] = [];
  popularSongs: SongSimple[] = [];
  userSongsWithFullInfo: Song[] = [];
  showAllSongs: boolean = false;
  showPopularSongs: boolean = false;
  styles: string[] = [];

  currentPage: number = 0;
  pageSize: number = 10;
  totalElements: number = 0;
  totalPages: number = 0;
  maxFavoriteSongs: number = 5;

  showFilter: boolean = false;
  notFound: boolean = false;
  styleFilter: string = '0';
  titleFilter?: string;
  artistFilter?: string;
  albumFilter?: string;

  constructor(private songService: MusicService,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.getRecentSongs();
    this.getStyles();
    this.getPopularSongs();
    this.getUserSongs();
  }

  useFilter(): void {
    this.currentPage = 0;
    if (!this.showAllSongs && !this.showPopularSongs) {
      this.getRecentSongs();
      this.getPopularSongs();
    } else if (this.showAllSongs) {
      this.getAllSongs();
    } else {
      this.getPopularSongs();
    }
  }

  deleteFilter(): void {
    this.titleFilter = '';
    this.artistFilter = '';
    this.albumFilter = '';
    this.styleFilter = '';
    this.useFilter();
  }

  getRecentSongs(): void {
    let filtersRecent: { [key: string]: string } | undefined = undefined;
    if (this.titleFilter || this.albumFilter || this.artistFilter || this.styleFilter !== '0') {
      filtersRecent = {};
      if (this.styleFilter !== '0') {
        filtersRecent['style'] = this.styleFilter;
      }
    }

    this.songService.getRecentSongs(this.currentPage, this.pageSize, filtersRecent).subscribe({
      next: (response: any) => {
        this.songs = response.content;
        this.totalElements = response.totalElements;
        this.totalPages = response.totalPages;
        this.notFound = this.songs.length === 0;
      },
      error: (error) => {
        console.log('Error showing recent songs', error);
      }
    });
  }

  getAllSongs(): void {
    let filters: { [key: string]: string } | undefined = undefined;
    if (this.titleFilter || this.albumFilter || this.artistFilter || this.styleFilter !== '0') {
      filters = {};
      if (this.titleFilter) {
        filters['title'] = this.titleFilter;
      }
      if (this.albumFilter) {
        filters['album'] = this.albumFilter;
      }
      if (this.artistFilter) {
        filters['artist'] = this.artistFilter;
      }
      if (this.styleFilter !== '0') {
        filters['style'] = this.styleFilter;
      }
    }

    this.songService.getAllSongs(this.currentPage, this.pageSize, filters).subscribe({
      next: (songs: any) => {
        this.songs = songs.content;
        this.totalElements = songs.totalElements;
        this.totalPages = Math.ceil(this.totalElements / this.pageSize);
        this.notFound = this.songs.length === 0;
      },
      error: (err) => {
        this.handleError(err);
      }
    });
  }

  getPopularSongs(): void {
    let filterReproductions: { [key: string]: string } | undefined = undefined;
    if (this.styleFilter !== '0') {
      filterReproductions = {style: this.styleFilter};
    }
    this.songService.getSongsByReproductions(this.currentPage, this.pageSize, filterReproductions).subscribe({
      next: songs => {
        this.popularSongs = songs;
        this.totalPages = Math.ceil(this.totalElements / this.pageSize);
        this.getUserSongs();
        this.notFound = this.popularSongs.length === 0;
      },
      error: (err) => {
        this.handleError(err);
      }
    });
  }

  getUserSongs(): void {
    const username = this.authService.getUsername();
    if (!username) {
      return;
    }
    this.authService.getUserIdByUsername(username).pipe(
      switchMap((userId: number) => {
        return this.songService.getFavoriteSongs(userId, this.currentPage, this.pageSize).pipe(
          catchError((err) => {
            this.handleError(err);
            return EMPTY;
          })
        );
      }),
      catchError((err) => {
        this.handleError(err);
        return EMPTY;
      })
    ).subscribe((favoriteSongs) => {
      if (Array.isArray(favoriteSongs.content)) {
        const observables = favoriteSongs.content.map((userSong: UserSong) => {
          return this.songService.getSong(userSong.songId);
        });
        forkJoin<Song[]>(observables).subscribe((songs: Song[]) => {
          this.userSongsWithFullInfo = songs.map(song => {
            return {
              id: song.id,
              title: song.title,
              imageAlbum: song.imageAlbum,
              artistName: song.artistName,
              album: song.album,
              style: song.style,
            };
          });
        }, error => {
          console.error('Error loading song images:', error);
        });
      } else {
        console.error('Not an Array:', favoriteSongs);
      }
    });
  }

  toggleShowAllSongs(): void {
    this.showAllSongs = !this.showAllSongs;
    if (!this.showAllSongs) {
      this.getRecentSongs();
      this.getUserSongs();
      this.getPopularSongs();
    } else {
      this.popularSongs = [];
      this.userSongsWithFullInfo = [];
      this.getAllSongs();
    }
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      if (!this.showAllSongs) {
        this.getRecentSongs();
      } else {
        this.getAllSongs();
      }
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      if (!this.showAllSongs) {
        this.getRecentSongs();
      } else {
        this.getAllSongs();
      }
    }
  }

  getPageNumbers(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  goToPage(pageNumber: number): void {
    this.currentPage = pageNumber - 1;
    if (!this.showAllSongs) {
      this.getRecentSongs();
    } else {
      this.getAllSongs();
    }
  }

  getStyles(): void {
    this.songService.getStyles(this.currentPage, this.pageSize, this.styleFilter)
      .subscribe({
        next: (stylesResponse: any) => {
          this.styles = stylesResponse.content.map((item: any) => item.style);
        },
        error: (err) => {
          this.handleError(err);
        }
      });
  }

  filter(): void {
    this.showFilter = !this.showFilter;
  }

  private handleError(error: any): void {
    console.log(error);
  }
}
