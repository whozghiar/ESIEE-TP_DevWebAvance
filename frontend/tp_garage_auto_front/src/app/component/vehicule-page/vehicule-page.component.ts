import {Component, OnInit} from '@angular/core';
import {VehiculeCardComponent} from "../vehicule-card/vehicule-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";

@Component({
  selector: 'app-vehicule-page',
  standalone: true,
  imports: [
    VehiculeCardComponent,
    NgForOf,
    LoadingSpinnerComponent,
    NgIf
  ],
  templateUrl: './vehicule-page.component.html',
  styleUrl: './vehicule-page.component.css'
})
export class VehiculePageComponent implements OnInit {

  vehicules: Vehicule[] = [];
  isLoadingVehicules = true;

  constructor(
    private vehiculeService: VehiculeService,
    private authguardService: AuthguardService
  ) { }

  ngOnInit(): void {
    this.loadVehicules();
  }

  /**
   * Appelle le service pour récupérer la liste des véhicules
   */
  loadVehicules(): void {
    this.vehiculeService.getVehicules().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.vehicules = response.body;
      }
      this.isLoadingVehicules = false;
    });
  }

  /**
   * Supprime un véhicule de la liste
   * @param index
   */
  onVehiculeDeleted(index: number): void {
    this.vehicules.splice(index, 1);
  }
}
