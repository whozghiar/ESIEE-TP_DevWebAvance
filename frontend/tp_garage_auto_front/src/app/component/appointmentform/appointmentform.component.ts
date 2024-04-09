import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgForOf } from '@angular/common';
import { TechnicianService } from '../../services/technician.service';
import { VehicleService } from '../../services/vehicle.service';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-appointmentform',
  standalone: true,
  imports: [FormsModule, NgForOf],
  templateUrl: './appointmentform.component.html',
  styleUrl: './appointmentform.component.css',
})
export class AppointmentformComponent {
  appointment: any = {
    date: '',
    time: '',
    vehicle: '',
    client: '',
    description: '',
  };

  technicians: any[] = [];
  vehicles: any[] = [];

  ngOnInit() {
    this.technicians = this.technicianService.getAllTechnicians();
    this.vehicles = this.vehicleService.getAllVehicles();
  }

  // Exemple de données pour le calendrier

  constructor(
    private technicianService: TechnicianService,
    private vehicleService: VehicleService,
    protected appointmentService: AppointmentService,
  ) {}
}
