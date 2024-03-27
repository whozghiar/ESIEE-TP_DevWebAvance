import { Component } from '@angular/core';

import {FormsModule} from "@angular/forms";
import {VehicleService} from "../../services/vehicle.service";
import {VehiculeComponent} from "../vehicule/vehicule.component";
import {NgForOf} from "@angular/common";


@Component({
  selector: 'app-vehiculepage',
  standalone: true,
  imports: [
    FormsModule,
    VehiculeComponent,
    NgForOf
  ],
  templateUrl: './vehiculepage.component.html',
  styleUrl: './vehiculepage.component.css'
})
export class VehiculepageComponent {

    vehicle: any = {
      technician: "",
      clientVehicle: "",
      issue: "",
      model: ""
    }
    constructor(protected vehicleService: VehicleService) {
    }

  handleTrigger(id: number) {
    this.vehicleService.removeVehicle(id);
  }
}
