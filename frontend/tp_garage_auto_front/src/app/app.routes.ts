import { Routes } from '@angular/router';
import {VehiculepageComponent} from "./component/vehiculepage/vehiculepage.component";
import {TechnicianpageComponent} from "./component/technicianpage/technicianpage.component";
import {VehiculeformComponent} from "./component/vehiculeform/vehiculeform.component";

export const routes: Routes = [
  { path: "technicien", component: TechnicianpageComponent },
  { path: "vehicules", component: VehiculepageComponent },
  { path: "dashboard", component: VehiculeformComponent},
  { path: "**", redirectTo: "dashboard" }
];
