import { Component, OnInit } from '@angular/core';
import { ModalComponent } from '../modal/modal.component';
import { PlantaService } from '../../services/planta.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'app-table',
  standalone: true,
  imports: [ModalComponent,CommonModule,FormsModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnInit{

  listPlantas?: any[] = []

  plantaSeleccionada?: any = {}

  constructor(private plantaService:PlantaService){}


  ngOnInit(): void{
    this.obtenerPlantas()
    this.listPlantas
    
  }
  
 
  
  obtenerPlantas(){
    this.plantaService.obtenerPlantas().subscribe( data => {
      this.listPlantas = data;
    }), (err: HttpErrorResponse) => {
      console.error("Error al obtener plantas", err)
    }

  }
  


  eliminarPlanta(plantaId:number) {
    this.plantaService.eliminarPlanta(plantaId).subscribe( (data) => {
      this.obtenerPlantas()
    }), (err:HttpErrorResponse) => {
      console.log("Error al eliminar la planta",err)
    }
  }



  agregarPlanta(nuevaPlanta: any) {
    this.plantaService.crearPlanta(nuevaPlanta).subscribe( () => {
      this.obtenerPlantas()
      Swal.fire({
        title: '¡Planta creada!',
        text: `La planta ${nuevaPlanta.nombre} en ${nuevaPlanta.pais} se ha creado correctamente.`,
        icon: 'success',
        confirmButtonColor: '#33a3aa',
        confirmButtonText: 'Aceptar'
      });

    }), (err:HttpErrorResponse) => {
      console.log("Error al agregar planta",err)
    }
  }

  editarPlanta(planta:any) {
    this.plantaSeleccionada = { ...planta };
  }

  actualizarPlanta(planta:any){
    this.plantaSeleccionada = { ...planta };
    this.plantaService.editarPlanta(this.plantaSeleccionada.id,this.plantaSeleccionada).subscribe( (data) => {
      Swal.fire({
        title: '¡Planta Actualizada!',
        text: `La planta se ha actualizado correctamente.`,
        icon: 'success',
        confirmButtonColor: '#33a3aa',
        confirmButtonText: 'Aceptar'
      });
      
      this.obtenerPlantas()
    }), (err:HttpErrorResponse) => {
      console.log("Error al Actualizar la planta",err)
    }
  }


  // documentacion de SwartAlert2
  showModal(planta:any) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Queres eliminar la planta ${planta.nombre} ubicada en ${planta.pais}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#33a3aa',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.eliminarPlanta(planta.id); 
        Swal.fire(
          'Eliminada',
          'La planta ha sido eliminada correctamente.',
          'success'
        );
      }
    });
  }
  


}

