import { Component } from '@angular/core';
import { TechnicianComponent } from '../technician/technician.component';
import { FormsModule } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { VehiculeComponent } from '../vehicule/vehicule.component';
import {NgClass, NgForOf, NgStyle} from '@angular/common';
import { TechnicianpageComponent } from '../technicianpage/technicianpage.component';

@Component({
  selector: 'app-vehiculepage',
  standalone: true,
  imports: [
    FormsModule,
    TechnicianpageComponent,
    VehiculeComponent,
    NgForOf,
    NgStyle,
    NgClass,
  ],
  templateUrl: './vehiculepage.component.html',
  styleUrl: './vehiculepage.component.css',
})
export class VehiculepageComponent {
  vehicles: any = [{
    id: 0,
    technician: "jeo",//TechnicianComponent,
    client: "Jack",
    issue: "Motor broken",
    model: "Peugeot 206",
    status: false
  },{
    id: 1,
    technician: "jo",//TechnicianComponent,
    client: "Benito",
    issue: "Motor broken",
    model: "Peugeot 208",
    status: true,
  },{
    id: 2,
    technician: "jo",//TechnicianComponent,
    client: "Benito",
    issue: "Motor broken",
    model: "Peugeot 208",
    status: true,
  },];


  //temporaire
  technicians: any = [
    { id: 1, name: 'John', surname: 'Padawan' },
    { id: 2, name: 'Lucien', surname: 'Bobby' },
    { id: 3, name: 'Theo', surname: 'Lomege' },
  ];

  constructor(protected vehicleService: VehicleService) {}

  handleTrigger(id: number) {
    this.vehicleService.removeVehicle(id);
  }
}
