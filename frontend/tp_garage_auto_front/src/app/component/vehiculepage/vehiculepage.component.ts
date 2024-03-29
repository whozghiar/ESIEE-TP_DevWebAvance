import { Component } from '@angular/core';
import { TechnicianComponent } from '../technician/technician.component';
import { FormsModule } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { VehiculeComponent } from '../vehicule/vehicule.component';
import { NgForOf, NgStyle } from '@angular/common';
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
  ],
  templateUrl: './vehiculepage.component.html',
  styleUrl: './vehiculepage.component.css',
})
export class VehiculepageComponent {
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
