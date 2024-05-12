import { Component } from '@angular/core';
import {AuthService} from "../../auth/service/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  credentials: any = {};
  errorMessage: string = '';

  constructor(private authService: AuthService,
              private router: Router,
              private toast: ToastrService) { }

  login(): void {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.errorMessage = '';
        this.authService.saveToken(response.token);
        this.router.navigate(['/home']);
        this.toast.success('Welcome', 'Success');
      },
      error: (error) => {
        console.error('Login failed', error);
        this.errorMessage = 'Error en la autenticación. Por favor, revisa tus credenciales e intenta de nuevo.';
      }
    });
  }
}
