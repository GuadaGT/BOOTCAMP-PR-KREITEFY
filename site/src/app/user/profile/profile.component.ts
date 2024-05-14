import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/service/auth.service";
import {UserService} from "../service/user.service";
import {MusicService} from "../../music/service/music.service";
import {catchError, EMPTY, forkJoin, switchMap, tap} from "rxjs";
import { HttpErrorResponse } from "@angular/common/http";
import {Song} from "../../model/Song.interface";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  username: string | null = null;
  userId: number | null = null;
  user: any = {
    username: '',
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  };
  newPassword: string = '';
  confirmPassword: string = '';
  passwordsMatchError: boolean = false;

  currentPage: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;
  totalPages: number = 0;
  userSongsWithFullInfo: Song[] = [];

  constructor(private authService: AuthService,
              private userService: UserService,
              private songService: MusicService,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.username = this.authService.getUsername();
    if (this.username) {
      this.authService.getUserIdByUsername(this.username).subscribe(
        (userId: number) => {
          this.userId = userId;
          this.userService.getUserById(this.userId!).subscribe(
            (user: any) => {
              const { password, ...userDataWithoutPassword } = user;
              this.user = userDataWithoutPassword;
              this.pageSize = 5;
              this.moreListenedByUser();
            },
            (error: any) => {
              this.toastr.error('Error while updating user');
            }
          );
        },
        (error: any) => {
          this.toastr.error('Error retrieving user ID');
        }
      );
    }
  }

  checkPasswords() {
    this.passwordsMatchError = this.user.password !== this.user.confirmPassword;
  }

  areAllFieldsFilled(): boolean {
    return this.user.username && this.user.email && this.user.firstName && this.user.lastName && this.user.password && this.user.confirmPassword;
  }

  userForm(): void {
    if (this.newPassword !== this.confirmPassword) {
      this.passwordsMatchError = true;
        return;
    }
    this.passwordsMatchError = false;
    if (this.areAllFieldsFilled()) {
      this.userService.updateUser(this.user).subscribe(
        (response: any) => {
          this.toastr.success('User updated successfully');
          console.log('User updated successfully:', response);
        },
        (error: HttpErrorResponse) => {
          console.error('Error updating user:', error);
          this.toastr.error('Error updating user: ' + error.statusText);
        }
      );
    } else {
      console.error('Not all fields are filled');
    }
  }

  moreListenedByUser(): void {
    const username = this.authService.getUsername();
    if (!username) {
      console.error('Username not found');
      return;
    }

    this.authService.getUserIdByUsername(username).pipe(
      switchMap((userId: number) => {
        return this.songService.getReproductionsCount(userId).pipe(
          tap((count: number) => {
            this.totalElements = count;
            this.totalPages = Math.ceil(this.totalElements / this.pageSize);
          }),
          switchMap(() => {
            return this.songService.getReproductions(userId, this.currentPage, this.pageSize);
          })
        );
      }),
      catchError((err) => {
        this.handleError(err);
        return EMPTY;
      })
    ).subscribe((reproductions) => {
      if (Array.isArray(reproductions)) {
        const observables = reproductions.map((reproduction) => {
          return this.songService.getSong(reproduction.songId);
        });
        forkJoin<Song[]>(observables).subscribe((songs: Song[]) => {
          this.userSongsWithFullInfo = songs.map((song, index) => {
            return {
              id: song.id,
              title: song.title,
              imageAlbum: song.imageAlbum,
              artistName: song.artistName,
              album: song.album,
              style: song.style,
              reproductionDate: reproductions[index].reproductionDate
            };
          });
        }, error => {
          console.error('Error loading songs', error);
        });
      } else {
        console.error('Not an Array:', reproductions);
      }
    });
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.pageSize = 5
      this.moreListenedByUser();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.pageSize = 5;
      this.moreListenedByUser();
    }
    console.log(this.currentPage);
  }

  private handleError(error: any): void {
    console.log(error);
  }
}
