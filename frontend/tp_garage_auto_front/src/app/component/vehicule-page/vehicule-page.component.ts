import {Component, OnInit} from '@angular/core';
import {VehiculeCardComponent} from "../vehicule-card/vehicule-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";

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

  isLoading = true;

  constructor(private vehiculeService: VehiculeService) { }

  ngOnInit(): void {
    this.vehiculeService.getVehicules().subscribe(response => {
      if (response.body !== null) {
        this.vehicules = response.body;
      }
      this.isLoading = false;
    });
  }

  onVehiculeDeleted(index: number): void {
    this.vehicules.splice(index, 1);
  }
}
