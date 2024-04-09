import { Routes } from '@angular/router';
import { VehiculepageComponent } from './component/vehiculepage/vehiculepage.component';
import { TechnicianpageComponent } from './component/technicianpage/technicianpage.component';
import { VehiculeformComponent } from './component/vehiculeform/vehiculeform.component';
import { AppointmentpageComponent } from './component/appointmentpage/appointmentpage.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { ClientpageComponent } from './component/clientpage/clientpage.component';
import { ClientformComponent } from './component/clientform/clientform.component';
import { TechnicianformComponent } from './component/technicianform/technicianform.component';
import { AppointmentformComponent } from './component/appointmentform/appointmentform.component';

export const routes: Routes = [
  { path: 'techniciens', component: TechnicianpageComponent },
  { path: 'techniciens/:id', component: TechnicianformComponent },
  { path: 'vehicules', component: VehiculepageComponent },
  { path: 'vehicules/:id', component: VehiculeformComponent },
  { path: 'appointments', component: AppointmentpageComponent },
  { path: 'appointments/:id', component: AppointmentformComponent },
  { path: 'clients', component: ClientpageComponent },
  { path: 'client/:id', component: ClientformComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '**', redirectTo: 'dashboard' },
];
