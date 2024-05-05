import { Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import {HomeComponent} from "./component/home/home.component";
import {RendezVousFormComponent} from "./component/rendez-vous-form/rendez-vous-form.component";
import {ClientPageComponent} from "./component/client-page/client-page.component";
import {TechnicienPageComponent} from "./component/technicien-page/technicien-page.component";
import {VehiculePageComponent} from "./component/vehicule-page/vehicule-page.component";
import {RendezVousPageComponent} from "./component/rendez-vous-page/rendez-vous-page.component";
import {DepotComponent} from "./component/depot/depot.component";
import {UtilisateurProfilComponent} from "./component/utilisateur-profil/utilisateur-profil.component";
import {AuthguardService} from "./services/AuthGuardService/authguard.service";
import {AdminAuthGuardService} from "./services/AdminAuthGuardService/admin-auth-guard.service";
import {ClientAuthGuardService} from "./services/ClientAuthGuardService/client-auth-guard.service";


export const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'dashboard', component: DashboardComponent },

  { path: 'client', component: ClientPageComponent, canActivate: [AdminAuthGuardService]},
  { path: 'technicien', component: TechnicienPageComponent, canActivate: [AdminAuthGuardService]},
  { path: 'vehicule', component: VehiculePageComponent, canActivate: [AdminAuthGuardService]},

  { path: 'calendrier', component:RendezVousPageComponent},

  { path: 'depot', component: DepotComponent},

  { path: 'utilisateur', component: UtilisateurProfilComponent, canActivate : [ClientAuthGuardService]},

  { path: '**', redirectTo: 'home' },
];
