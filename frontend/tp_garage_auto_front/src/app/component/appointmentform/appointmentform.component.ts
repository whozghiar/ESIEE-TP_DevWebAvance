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
    typeService: '',
    vehicule: '',
    technicien: '',
  };

  techniciens: any[] = [];
  vehicules: any[] = [];

  ngOnInit() {
    this.techniciens = this.technicianService.getAllTechnicians();
    this.vehicules = this.vehicleService.getAllVehicles();
  }

  // Exemple de données pour le calendrier

  constructor(
    private technicianService: TechnicianService,
    private vehicleService: VehicleService,
    protected appointmentService: AppointmentService,
  ) {}

  logAppointment() {
    console.log(this.appointment, 'appointment form');
  }
}
