import { Component } from '@angular/core';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { CardComponent } from "../../components/card/card.component";
import { TableComponent } from '../../components/table/table.component';
import { StatusCardComponent } from '../../components/status-card/status-card.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ SidebarComponent, NavbarComponent, CardComponent,TableComponent,StatusCardComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
