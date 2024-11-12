import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../enviroment';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
                  
  private baseUrl = environment.baseUrl

  private userKey = 'user';
  
  constructor(private httpCliente:HttpClient) { }

  // metodos de localStorage para simular login
  setUser(user: any) {
    localStorage.setItem(this.userKey, JSON.stringify(user));
  }

  getUser() {
    const user = localStorage.getItem(this.userKey);
    return user ? JSON.parse(user) : null;
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }

  logout() {
    localStorage.clear()
  }




  // metodos del backend 

  obtenerUsuario(usuarioId:number):Observable<any>{
    return this.httpCliente.get<any>(`${this.baseUrl}usuario/${usuarioId}`)
  }

  crearUsuario(usuario:any):Observable<any>{
    return this.httpCliente.post(`${this.baseUrl}usuario`,usuario)
  }

  login(usuario:any):Observable<any>{
    return this.httpCliente.post(`${this.baseUrl}usuario/login`,usuario)
  }




}
