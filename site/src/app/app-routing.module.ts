import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./user/login/login.component";
import {RegisterComponent} from "./user/register/register.component";
import {HomeComponent} from "./home/home.component";
import {SongsComponent} from "./music/songs/songs.component";
import {ProfileComponent} from "./user/profile/profile.component";
import {SongComponent} from "./music/song/song.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full' },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path:'profile',
    component: ProfileComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'songs',
    component: SongsComponent
  },
  {
    path: 'song/:id',
    component: SongComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
