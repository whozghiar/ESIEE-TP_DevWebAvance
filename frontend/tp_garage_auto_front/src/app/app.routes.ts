import { Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import {HomeComponent} from "./component/home/home.component";
import {RendezVousFormComponent} from "./component/rendez-vous-form/rendez-vous-form.component";
import {ClientPageComponent} from "./component/client-page/client-page.component";

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'client-page', component: ClientPageComponent},
  { path: 'home', component: HomeComponent},
  { path:'rendez-vous-form', component:RendezVousFormComponent},
  { path: '**', redirectTo: 'home' },
];
