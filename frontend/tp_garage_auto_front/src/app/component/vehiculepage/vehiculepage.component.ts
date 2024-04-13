import { Component } from '@angular/core';
import { TechnicianComponent } from '../technician/technician.component';
import { FormsModule } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { VehiculeComponent } from '../vehicule/vehicule.component';
import { NgClass, NgForOf, NgStyle } from '@angular/common';
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
  vehicles: any = [];

  constructor(private vehicleService: VehicleService) {}

  ngOnInit() {
    this.vehicles = this.vehicleService.getAllVehicles();
    console.log(this.vehicles, 'vehicles page véhicule');
  }

  /*constructor(protected vehicleService: VehicleService) {
    this.vehicles = this.vehicleService.getAllVehicles();
    console.log(this.vehicles, 'vehicles page véhicule');
  }*/

  handleTrigger(id: number) {
    this.vehicleService.removeVehicle(id);
  }
}
