import { Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import {HomeComponent} from "./component/home/home.component";
import {ClientPageComponent} from "./component/client-page/client-page.component";
import {TechnicienPageComponent} from "./component/technicien-page/technicien-page.component";
import {VehiculePageComponent} from "./component/vehicule-page/vehicule-page.component";
import {RendezVousPageComponent} from "./component/rendez-vous-page/rendez-vous-page.component";
import {UtilisateurProfilComponent} from "./component/utilisateur-profil/utilisateur-profil.component";
import {AuthguardService} from "./services/AuthGuardService/authguard.service";
import {AdminAuthGuardService} from "./services/AdminAuthGuardService/admin-auth-guard.service";
import {ClientAuthGuardService} from "./services/ClientAuthGuardService/client-auth-guard.service";
import {TechnicienAuthGuardService} from "./services/TechnicienAuthGuardService/technicien-auth-guard.service";
import {AdminOrTechnicienAuthGuardService} from "./services/AdminOrTechnicienAuthGuardService/admin-auth-guard.service";
import {RendezVousCalendrierComponent} from "./component/rendez-vous-calendrier/rendez-vous-calendrier.component";


export const routes: Routes = [
  { path: 'home', component: HomeComponent},

  { path: 'client', component: ClientPageComponent, canActivate: [AdminAuthGuardService]},
  { path: 'technicien', component: TechnicienPageComponent, canActivate: [AdminAuthGuardService]},
  { path: 'vehicule', component: VehiculePageComponent, canActivate: [AdminAuthGuardService]},
  { path: 'rendez-vous', component: RendezVousPageComponent, canActivate: [AdminOrTechnicienAuthGuardService]},

  { path: 'calendrier', component: RendezVousCalendrierComponent, canActivate:[ClientAuthGuardService]},

  { path: 'profil', component: UtilisateurProfilComponent, canActivate : [ClientAuthGuardService]},

  { path: '**', redirectTo: 'home' },
];
