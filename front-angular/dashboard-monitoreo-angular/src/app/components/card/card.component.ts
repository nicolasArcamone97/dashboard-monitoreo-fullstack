import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PlantaService } from '../../services/planta.service';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})


export class CardComponent implements OnInit {

  listPlantas: any[] = [];

  cardsPlantas: any[] = [
    {titulo: "Lecturas Ok", value: 0, icono: 'bi-check-lg', claseIcono: 'circle'},
    {titulo: "Alertas medias", value: 0, icono: 'bi-exclamation-lg', claseIcono: 'circle-yell'},
    {titulo: "Alertas rojas", value: 0, icono: 'bi-exclamation-triangle', claseIcono: 'circle-red'},
    {titulo: "Sensores Deshabilitados", value: 0, icono: 'bi-x-lg', claseIcono: 'circle-orange'}
  ];

  constructor(private plantaService: PlantaService) {
    this.obtenerPlantas();
  }

  ngOnInit(): void {
    this.obtenerPlantas(); 
  }

  obtenerPlantas(): void {
    this.plantaService.obtenerPlantas().subscribe((data) => {
      this.listPlantas = data;
      this.cantidadAlertas();
    });
  }


  cantidadAlertas(): void {
    let lecturas: number = 0;
    let alertAlta: number = 0;
    let alertMedia: number = 0;
    let sensores: number = 0;

    if (this.listPlantas && this.listPlantas.length > 0) {
      this.listPlantas.forEach(planta => {
        lecturas += planta.cantLecturas; 
        alertAlta += planta.cantAlertasAltas;
        alertMedia += planta.cantAlertasMedias;
        sensores += planta.cantSensores;
      });

     
      this.cardsPlantas = [
        {titulo: "Lecturas Ok", value: lecturas, icono: 'bi-check-lg', claseIcono: 'circle'},
        {titulo: "Alertas medias", value:alertMedia, icono: 'bi-exclamation-lg', claseIcono: 'circle-yell'},
        {titulo: "Alertas rojas", value: alertAlta , icono: 'bi-exclamation-triangle', claseIcono: 'circle-red'},
        {titulo: "Sensores Deshabilitados", value: sensores, icono: 'bi-x-lg', claseIcono: 'circle-orange'}
      ];
      console.log('CardsPlantas:', this.cardsPlantas);
    } else {
      console.log('No hay plantas disponibles');
    }
  }
}