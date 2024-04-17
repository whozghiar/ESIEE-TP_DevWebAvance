import { Component } from '@angular/core';
import { NgForOf } from '@angular/common';
import { TechnicianComponent } from '../technician/technician.component';
import { TechnicianformComponent } from '../technicianform/technicianform.component';
import { AppointmentformComponent } from '../appointmentform/appointmentform.component';
import { VehicleService } from '../../services/vehicle.service';
import { TechnicianService } from '../../services/technician.service';

@Component({
  selector: 'app-technicianpage',
  standalone: true,
  imports: [
    NgForOf,
    TechnicianComponent,
    TechnicianformComponent,
    AppointmentformComponent,
  ],
  templateUrl: './technicianpage.component.html',
  styleUrl: './technicianpage.component.css',
})
export class TechnicianpageComponent {
  technicians: any = [];

  constructor(private technicianService: TechnicianService) {
    this.technicians = this.technicianService.getAllTechnicians();
    console.log(this.technicians, 'technicians page technicien');
  }
}
