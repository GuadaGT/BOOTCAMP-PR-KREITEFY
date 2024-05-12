import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MusicService} from "../service/music.service";
import {Song} from "../../model/Song.interface";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../../auth/service/auth.service";
import {NgbRatingConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.scss'],
  providers: [NgbRatingConfig]
})
export class SongComponent implements OnInit {
  songId!: number;
  song?: Song;
  userId?: number;

  constructor(private route: ActivatedRoute,
              private musicService: MusicService,
              private authService: AuthService,
              private toastr: ToastrService,
              rating: NgbRatingConfig)
  {
    rating.max = 4;
  }

  ngOnInit(): void {
    const username = this.authService.getUsername();
    if (username) {
      this.authService.getUserIdByUsername(username).subscribe({
        next: (userId) => {
          this.userId = userId;
          this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
              this.songId = +id;
              this.getSong();
            }
          });
        },
        error: (error) => {
          console.error('Error getting user ID:', error);
        }
      });
    } else {
      console.error('Username not available');
    }
  }

  getSong(): void {
    this.musicService.getSong(this.songId).subscribe({
      next: (song) => {
        this.song = song;
      },
      error: (error) => {
        console.error('Error getting song:', error);
      }
    });
  }

  addReproduction() {
    if (this.userId) {
      const userSong = { songId: this.songId, userId: this.userId };
      this.musicService.addReproductions(userSong).subscribe({
        next: (response) => {
          this.toastr.success('Added to your list', 'Success');
        },
        error: (error) => {
          this.toastr.error('Song is not added to your list', 'Error');
        }
      });
    } else {
      console.error('User ID not available');
    }
  }

  addRating(rating: number) {
    if (this.userId) {
      const userSong = { songId: this.songId, userId: this.userId };
      this.musicService.addRating(userSong,rating).subscribe({
        next: (response) => {
          this.toastr.success('Song rated succesfully', 'Success');
        },
        error: (error) => {
          this.toastr.error( 'Song is not rated yet', 'Error');
          console.log(userSong.songId);
          console.log(userSong.userId);
        }
      });
    } else {
      console.error('User ID not available');
    }
  }
}
