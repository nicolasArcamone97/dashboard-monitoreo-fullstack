import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../enviroment';

@Injectable({
  providedIn: 'root'
})
export class PlantaService {

  private baseUrl = environment.baseUrl

  constructor(private httpCliente:HttpClient) { }



  public obtenerPlantas():Observable<any[]>{
    return this.httpCliente.get<any[]>(`${this.baseUrl}planta`)
  }

  public eliminarPlanta(plantaId:number):Observable<any>{
    return this.httpCliente.delete(`${this.baseUrl}planta/${plantaId}`)
  }

  public crearPlanta(nuevaPlanta:any):Observable<any>{
    return this.httpCliente.post(`${this.baseUrl}planta`,nuevaPlanta)
  }

  public editarPlanta(id:number,plantaEdit:any):Observable<any>{
    return this.httpCliente.put<any>(`${this.baseUrl}planta/${id}`, plantaEdit)
  }


}
