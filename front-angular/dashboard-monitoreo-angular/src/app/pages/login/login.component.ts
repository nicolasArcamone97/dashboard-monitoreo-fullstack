import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule,CommonModule], 
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})



export class LoginComponent {

  loginForm: FormGroup
  
  constructor(private usuarioService: UsuarioService, private router: Router, private fb: FormBuilder) {
    
  this.loginForm =  this.fb.group({
      email: ['', [Validators.required, Validators.email]], 
      password: ['', [Validators.required, Validators.minLength(8)]] 
    });
  }
  

  onLogin() {
    if (this.loginForm.valid) {
      const usuarioLogin = this.loginForm.value; 
      this.usuarioService.login(usuarioLogin).subscribe({
        next: (response) => {
          console.log('Inicio de sesión exitoso:', response.user.email);
          if (response.user) {
            localStorage.setItem('user', JSON.stringify(response.user));
          }
          this.router.navigate(['/dashboard']);
        },
        error: (err: HttpErrorResponse) => {
          console.error("Error al Iniciar sesion", err);
          alert('Por favor ingresa datos validos')
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}
