import { Component } from '@angular/core';
import { NgForOf } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { ClientService } from '../../services/client.service';
import { TechnicianService } from '../../services/technician.service';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-vehiculeform',
  standalone: true,
  imports: [NgForOf, ReactiveFormsModule, FormsModule],
  templateUrl: './vehiculeform.component.html',
  styleUrl: './vehiculeform.component.css',
})
export class VehiculeformComponent {
  vehicle: any = {
    id: Number,
    marque: '',
    modele: '',
    immatriculation: '',
    annee: '',
    rendezVous: [],
    client: '',
  };

  clients: any = [];
  appointments: any = [];

  constructor(
    protected vehicleService: VehicleService,
    protected clientService: ClientService,
    protected appointmentService: AppointmentService,
  ) {}

  ngOnInit() {
    this.vehicle.appointmentsVehicle =
      this.appointmentService.getAllAppointments();
    this.vehicle.clients = this.clientService.getAllClients();
  }

  handleTrigger(id: number) {
    this.vehicleService.removeVehicle(id);
  }
}
