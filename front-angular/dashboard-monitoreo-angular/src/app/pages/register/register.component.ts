import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,RouterLink], 
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm: FormGroup;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.registerForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  


  onSubmit() {
    if (this.registerForm.valid) {
      const user = this.registerForm.value;

      this.usuarioService.crearUsuario(user).subscribe({
        next: (response) => {
          console.log('Usuario registrado:', response);
          this.router.navigate(['/login']);
        },
        error: (err: HttpErrorResponse) => {
          console.error("Error al crear usuario", err);
          alert('Por favor ingresa datos validos')
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }
}
