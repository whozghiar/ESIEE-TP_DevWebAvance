import { Routes } from '@angular/router';
import {VehiculepageComponent} from "./component/vehiculepage/vehiculepage.component";

export const routes: Routes = [
  //{path: "rdv", component: NavBarComponent},
  {path: "vehicule", component: VehiculepageComponent},
  {path: "**", redirectTo: "VehiculepageComponent"}
];
