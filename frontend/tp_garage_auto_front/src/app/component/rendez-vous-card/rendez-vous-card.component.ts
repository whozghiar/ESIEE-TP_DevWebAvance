import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";
import {NgIf} from "@angular/common";
import {Technicien} from "../../modeles/TechnicienModele/technicien";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {RendezVousModifierComponent} from "../rendez-vous-modifier/rendez-vous-modifier.component";

@Component({
  selector: 'app-rendez-vous-card',
  standalone: true,
  imports: [
    NgIf,
    RendezVousModifierComponent
  ],
  templateUrl: './rendez-vous-card.component.html',
  styleUrl: './rendez-vous-card.component.css'
})
export class RendezVousCardComponent implements OnInit{

  @Input() rendezVous!: RendezVous;

  @Output() refresh = new EventEmitter<void>();

  isEditing : boolean = false;

  constructor(
    protected authguardService: AuthguardService,
    private rendezVousService : RendezVousService
  ) {}

  ngOnInit(): void {
  }

  /**
   * Appelle le service RendezVous pour supprimer un rendez-vous
   */
  deleteRendezVous() {
    if (this.rendezVous.id != null) {
      this.rendezVousService.deleteRendezVous(this.rendezVous.id).subscribe(response => {
        if (response.status === 204) {
          this.refresh.emit();
          console.log('Rendez-vous deleted');
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
