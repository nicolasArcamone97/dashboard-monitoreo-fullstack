import { Component } from '@angular/core';
import { Router, RouterLink} from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {


  constructor(private usuarioService: UsuarioService,
              private router:Router
  ){}


  
  logout(){
    this.usuarioService.logout()
    this.router.navigate(['/register'])
  }

}
