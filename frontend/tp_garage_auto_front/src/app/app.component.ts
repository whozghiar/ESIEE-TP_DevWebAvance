import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { VehiculepageComponent } from './component/vehiculepage/vehiculepage.component';
import { TechnicianService } from './services/technician.service';
import { ClientService } from './services/client.service';
import { AppointmentService } from './services/appointment.service';
import { VehicleService } from './services/vehicle.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavBarComponent, VehiculepageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'tp_garage_auto_front';
  clients: any[] = [];
  appointments: any[] = [];
  technicians: any[] = [];
  vehicles: any[] = [];

  constructor(
    private clientService: ClientService,
    private appointmentService: AppointmentService,
    private technicianService: TechnicianService,
    private vehicleService: VehicleService,
  ) {}

  ngOnInit() {
    this.clients = this.clientService.getAllClients();
    this.appointments = this.appointmentService.getAllAppointments();
    this.technicians = this.technicianService.getAllTechnicians();
    this.vehicles = this.vehicleService.getAllVehicles();
  }
}
