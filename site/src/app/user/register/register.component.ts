import { Component } from '@angular/core';
import {AuthService} from "../../auth/service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  user: any = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    rol: 'USER'
  };

  constructor(private authService: AuthService,
              private router: Router,
              private toast: ToastrService) { }

  register(userForm: NgForm): void {
    if (userForm.invalid) {
      this.toast.error('Invalid Form!');
      return;
    }
    this.authService.register(this.user).subscribe({
      next: (response) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/home']);
        this.toast.success('User registered successfully', 'Success');
      },
      error: (error) => {
        console.error('There was an error!', error);
      }
    });
  }

  resetUser(): void {
    this.router.navigate(['/home']);
  }
}
