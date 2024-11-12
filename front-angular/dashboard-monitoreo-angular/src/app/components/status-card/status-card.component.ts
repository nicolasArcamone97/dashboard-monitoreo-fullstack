import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-status-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './status-card.component.html',
  styleUrl: './status-card.component.css'
})
export class StatusCardComponent {

  cardsStatus?: any[] = [
    { title: "Temperatura", values: [100, 20, 3], icon: "thermostat" },
    { title: "Presión", values: [95, 15, 4], icon: "speed" },
    { title: "Viento", values: [80, 10, 2], icon: "air" },
    { title: "Nivel", values: [120, 25, 1], icon: "filter_list" },
    { title: "Energía", values: [90, 18, 5], icon: "energy_savings_leaf" },
    { title: "Tensión", values: [90, 18, 5], icon: "offline_bolt" },
    { title: "Monóxido de carbono", values: [90, 18, 5], icon: "co2" },
    { title: "Otros gases", values: [90, 18, 5], icon:"cloud" }
  ]

  constructor(){}


  // paso siguiente desable que sean datos dinamicos desde el back


}
