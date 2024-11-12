import { Component, EventEmitter, Output } from '@angular/core';
import { PaisService } from '../../services/pais.service';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})

export class ModalComponent {

  listPaises: any[] = []; 
  paisSelected?: any;       
  nombreIngresado: string = '';
  detallePais?:any

  @Output() plantaEnviada = new EventEmitter<any>();

  constructor(private paisService: PaisService) {}

  ngOnInit() {
    this.obtenerPaises();
   
  }


  obtenerPaises() {
    this.paisService.obtenerPaises().subscribe(paises => {
      this.listPaises = paises;
    }), (err:HttpErrorResponse) => {
      console.log("Error al obtener paises de api countries",err)
    }
  }

  obtenerPais(pais:any){
    this.paisService.obtenerPais(this.paisSelected).subscribe( (pais) => {
      this.detallePais = pais
      console.log(this.detallePais)
    }), (err:HttpErrorResponse) => {
      console.log("Error al obtener el pais de api countries", err)
    }
  }

   


  onSubmit() {

    const nuevaPlanta = {
      nombre: this.nombreIngresado,
      pais: this.detallePais[0].name.common,
      bandera: this.detallePais[0].flags.png
    };


    console.log(nuevaPlanta);
    this.plantaEnviada.emit(nuevaPlanta);

  }
}


