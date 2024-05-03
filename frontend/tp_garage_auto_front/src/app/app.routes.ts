import { Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import {HomeComponent} from "./component/home/home.component";
import {RendezVousFormComponent} from "./component/rendez-vous-form/rendez-vous-form.component";
import {ClientPageComponent} from "./component/client-page/client-page.component";
import {TechnicienPageComponent} from "./component/technicien-page/technicien-page.component";
import {VehiculePageComponent} from "./component/vehicule-page/vehicule-page.component";
import {RendezVousPageComponent} from "./component/rendez-vous-page/rendez-vous-page.component";
import {DepotComponent} from "./component/depot/depot.component";


export const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'dashboard', component: DashboardComponent },
  { path: 'client', component: ClientPageComponent},
  { path: 'technicien', component: TechnicienPageComponent},
  { path: 'vehicule', component: VehiculePageComponent},
  { path: 'rendez-vous-form', component:RendezVousFormComponent},
  { path: 'calendrier', component:RendezVousPageComponent},
  { path: 'depot', component: DepotComponent},
  { path: '**', redirectTo: 'home' },
];
