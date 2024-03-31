import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {VehicleService} from "../../services/vehicle.service";

@Component({
  selector: 'app-vehiculeform',
  standalone: true,
  imports: [
    NgForOf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './vehiculeform.component.html',
  styleUrl: './vehiculeform.component.css'
})
export class VehiculeformComponent {

  vehicle: any = {
    technician: '',
    clientVehicle: '',
    issue: '',
    model: '',
  };

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
