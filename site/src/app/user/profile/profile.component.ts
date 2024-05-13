import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/service/auth.service";
import {UserService} from "../service/user.service";
import {MusicService} from "../../music/service/music.service";
import {UserSong} from "../../model/UserSong.interface";
import {forkJoin} from "rxjs";
import {Song} from "../../model/Song.interface";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  username: string | null = null;
  userId: number | null = null;
  userSongs: UserSong[] = [];
  userSongsWithFullInfo: Song[] = [];
  user: any = {
    username: '',
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  };

  currentPage: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;
  totalPages: number = 0;

  constructor(private authService: AuthService,
              private userService: UserService,
              private songService: MusicService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    if (this.username) {
      this.authService.getUserIdByUsername(this.username).subscribe(
        (userId: number) => {
          this.userId = userId;
          this.userService.getUserById(this.userId!).subscribe(
            (user: any) => {
              this.user = user;
              this.moreListenedByUser();
            },
            (error: any) => {
              console.error('Error retrieving user details:', error);
            }
          );
        },
        (error: any) => {
          console.error('Error retrieving user ID:', error);
        }
      );
    }
  }

  userForm(): void {
    this.userService.updateUser(this.user).subscribe(
      (response: any) => {
        console.log('User updated successfully:', response);
      },
      (error: any) => {
        console.error('Error updating user:', error);
      }
    );
  }

  moreListenedByUser(): void {
    const username = this.authService.getUsername();
    if (!username) {
      console.error('Username not found');
      return;
    }
    this.authService.getUserIdByUsername(username).subscribe({
      next: (userId: number) => {
        this.songService.getFavoriteSongs(userId, this.currentPage, this.pageSize).subscribe({
          next: (favoriteSongs: any) => {
            if (Array.isArray(favoriteSongs.content)) {
              const userSongs: UserSong[] = favoriteSongs.content;
              this.userSongs = userSongs;
              const observables = userSongs.map((userSong: UserSong) => {
                return this.songService.getSong(userSong.songId);
              });
              forkJoin<Song[]>(observables).subscribe((songs: Song[]) =>{
                this.userSongsWithFullInfo = songs.map((song, index) => {
                  return {
                    id: song.id,
                    title: song.title,
                    image: song.imageAlbum,
                    artistName: song.artistName,
                    album: song.album,
                    style: song.style,
                    date: song.date,
                    reproductionDate: userSongs[index].reproductionDate
                  };
                });
              }, error => {
                console.error('Error loading', error);
              });
            } else {
              console.error('Not an Array:', favoriteSongs);
            }
            this.totalElements = favoriteSongs.totalElements;
            this.calculateTotalPages();
          },
          error: (err) => {
            this.handleError(err);
          }
        });
      },
      error: (err) => {
        this.handleError(err);
      }
    });
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.moreListenedByUser();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages -1) {
      this.currentPage++;
      this.moreListenedByUser();
    }
    console.log(this.currentPage);
  }

  private calculateTotalPages(): void {
    this.totalPages = Math.ceil(this.totalElements / this.pageSize);
  }

  private handleError(error: any): void {
    console.log(error);
  }
}
