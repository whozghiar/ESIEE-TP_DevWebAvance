import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Client} from "../../modeles/ClientModele/client";
import {ClientService} from "../../services/ClientService/client.service";
import {ClientModifierComponent} from "../client-modifier/client-modifier.component";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-client-card',
  standalone: true,
  imports: [
    ClientModifierComponent,
    NgIf
  ],
  templateUrl: './client-card.component.html',
  styleUrl: './client-card.component.css'
})
export class ClientCardComponent {
  @Input() client!: Client;
  @Output() clientDeleted = new EventEmitter<Client>();
  isEditing = false; // Booléen permettant de savoir si le client est en cours de modification

  constructor(private clientService: ClientService) {
  }

  /**
   * Méthode permettant de supprimer un client depuis le composant client-card
   * Si le client a un id, on le supprime via le service clientService
   * On émet ensuite un événement clientDeleted avec le client supprimé
   */
  deleteClient() {
    if (this.client.id != null) {
      this.clientService.deleteClient(this.client.id).subscribe(response => {
        if (response.status === 204) {
          this.clientDeleted.emit(this.client);
          console.log('Client deleted');
        }
      });
    }
  }

  switchToModifier() {
    this.isEditing = true; // Lorsque l'utilisateur clique sur l'emoji de clé à molette (depuis le composant client-card)
  }

  switchToCard() {
    this.isEditing = false; // Lorsque l'utilisateur clique sur l'emoji de croix rouge (depuis le composant client-modifier)
  }
}
