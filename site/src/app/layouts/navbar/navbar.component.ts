import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router,
              private toast: ToastrService) {}

  getAuthService(): AuthService {
    return this.authService;
  }

  ngOnInit(): void {}

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logOut(){
    this.authService.logOut().subscribe({
      next: value => {
        this.authService.logOut();
        this.toast.error('Log out', 'Success')
        this.router.navigate(['/home']);
      },
      error:(err)=>{
       console.log(err);
        }
      }
    )
  }
}
