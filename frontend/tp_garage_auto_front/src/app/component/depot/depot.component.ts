import { Component } from '@angular/core';
import {ClientFormComponent} from "../client-form/client-form.component";
import {VehiculeFormComponent} from "../vehicule-form/vehicule-form.component";
import {RendezVousFormComponent} from "../rendez-vous-form/rendez-vous-form.component";
import {NgSwitch, NgSwitchCase} from "@angular/common";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {Client} from "../../modeles/ClientModele/client";

@Component({
  selector: 'app-depot',
  standalone: true,
  imports: [
    ClientFormComponent,
    VehiculeFormComponent,
    RendezVousFormComponent,
    NgSwitch,
    NgSwitchCase
  ],
  templateUrl: './depot.component.html',
  styleUrl: './depot.component.css'
})
export class DepotComponent {
  step = 1;
  client!: Client;
  vehicule!: Vehicule;

  onNextStepClient(client: Client) {
    this.client = client;
    this.step = 2;
  }

  onNextStepVehicule(vehicule: Vehicule) {
    this.vehicule = vehicule;
    this.step = 3;
  }

  onPreviousStep() {
    this.step--;
  }
}
