import { Routes } from '@angular/router';
import { VehiculepageComponent } from './component/vehiculepage/vehiculepage.component';
import { TechnicianpageComponent } from './component/technicianpage/technicianpage.component';
import { VehiculeformComponent } from './component/vehiculeform/vehiculeform.component';
import { AppointmentpageComponent } from './component/appointmentpage/appointmentpage.component';

export const routes: Routes = [
  { path: 'techniciens', component: TechnicianpageComponent },
  { path: 'vehicules', component: VehiculepageComponent },
  { path: 'appointments', component: AppointmentpageComponent },
  { path: 'vehicules/:id', component: VehiculeformComponent },
  { path: 'dashboard', component: VehiculeformComponent },
  { path: '**', redirectTo: 'dashboard' },
];
