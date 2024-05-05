import {Component, EventEmitter, Input, Output} from '@angular/core';
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {VehiculeModifierComponent} from "../vehicule-modifier/vehicule-modifier.component";
import {NgIf, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-vehicule-card',
  standalone: true,
  imports: [
    VehiculeModifierComponent,
    NgIf,
    NgOptimizedImage
  ],
  templateUrl: './vehicule-card.component.html',
  styleUrl: './vehicule-card.component.css'
})
export class VehiculeCardComponent {
  @Input() vehicule!: Vehicule;
  @Output() vehiculeDeleted = new EventEmitter<Vehicule>();
  isEditing = false;

  constructor(private vehiculeService: VehiculeService) { }

  deleteVehicule() {
    if (this.vehicule.id != null) {
      this.vehiculeService.deleteVehicule(this.vehicule.id).subscribe(response => {
        if (response.status === 204) {
          this.vehiculeDeleted.emit(this.vehicule);
          console.log('Vehicule deleted');
        }
      });
    }
  }

  switchToModifier() {
    this.isEditing = true;
  }

  switchToCard() {
    this.isEditing = false;
  }
}
