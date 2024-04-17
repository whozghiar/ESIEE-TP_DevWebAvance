import {Component, inject, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { VehiculepageComponent } from './component/vehiculepage/vehiculepage.component';
import { TechnicianService } from './services/technician.service';
import { ClientService } from './services/client.service';
import { AppointmentService } from './services/appointment.service';
import { VehicleService } from './services/vehicle.service';
import {OidcSecurityService} from "angular-auth-oidc-client";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavBarComponent, VehiculepageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit{

  title = 'tp_garage_auto_front';
  private readonly oidcSecurityService = inject(OidcSecurityService);

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
    this.oidcSecurityService.checkAuth().subscribe(({ isAuthenticated, userData,accessToken}) => {
      console.log('app authenticated', isAuthenticated);
      console.log('app userData', userData);
      console.log("app token", accessToken);

      if (!isAuthenticated) {
        this.login();

      }
    });
    this.clients = this.clientService.getAllClients();
    /*
    this.clients = this.clientService.getAllClients();
    this.appointments = this.appointmentService.getAllAppointments();
    this.technicians = this.technicianService.getAllTechnicians();
    this.vehicles = this.vehicleService.getAllVehicles();
     */


  }



  login() {
    this.oidcSecurityService.authorize();
  }

  logout() {
    this.oidcSecurityService.logoff().subscribe((result) => console.log(result));
  }
}


