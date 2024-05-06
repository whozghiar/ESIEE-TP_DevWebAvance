import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Technicien} from "../../modeles/TechnicienModele/technicien";
import {TechnicienService} from "../../services/TechnicienService/technicien.service";
import {TechnicienModifierComponent} from "../technicien-modifier/technicien-modifier.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-technicien-card',
  standalone: true,
  imports: [
    TechnicienModifierComponent,
    NgIf
  ],
  templateUrl: './technicien-card.component.html',
  styleUrl: './technicien-card.component.css'
})

export class TechnicienCardComponent {
  @Input() technicien!: Technicien;
  @Output() technicienDeleted = new EventEmitter<Technicien>();
  isEditing = false;

  constructor(private technicienService: TechnicienService) {
  }

  /**
   * Supprime le technicien
   */
  deleteTechnicien() {
    if (this.technicien.id != null) {
      this.technicienService.deleteTechnicien(this.technicien.id).subscribe(response => {
        if (response.status === 204) {
          this.technicienDeleted.emit(this.technicien);
          console.log('Technicien deleted');
        }
      });
    }
  }

  /**
   * Passe en mode modification
   */
  switchToModifier() {
    this.isEditing = true;
  }

  /**
   * Passe en mode affichage
   */
  switchToCard() {
    this.isEditing = false;
  }
}
