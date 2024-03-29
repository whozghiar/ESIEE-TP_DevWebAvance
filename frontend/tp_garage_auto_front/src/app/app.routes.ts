import { Routes } from '@angular/router';
import {VehiculepageComponent} from "./component/vehiculepage/vehiculepage.component";
import {TechnicianpageComponent} from "./component/technicianpage/technicianpage.component";

export const routes: Routes = [
  { path: "technicien", component: TechnicianpageComponent },
  { path: "vehicule", component: VehiculepageComponent },
  { path: "**", redirectTo: "vehicule"},
];
