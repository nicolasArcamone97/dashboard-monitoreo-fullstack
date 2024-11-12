import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaisService {

  // urls api de paises
  private baseUrl = 'https://restcountries.com/v3.1/all'

  private paisUrl = 'https://restcountries.com/v3.1/alpha?codes='

  constructor(private http:HttpClient) { }
  


  public obtenerPaises(): Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl)
  }

  public obtenerPais(code: any): Observable<any> {
    return this.http.get<any>(`${this.paisUrl}${code}`);
  }


}
