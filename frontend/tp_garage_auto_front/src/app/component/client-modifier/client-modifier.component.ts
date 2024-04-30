import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Client} from "../../modeles/ClientModele/client";
import {ClientService} from "../../services/ClientService/client.service";

@Component({
  selector: 'app-client-modifier',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './client-modifier.component.html',
  styleUrl: './client-modifier.component.css'
})
export class ClientModifierComponent {
  @Input() client!: Client;
  @Output() cancel = new EventEmitter<void>(); // Ajoutez cette ligne
  constructor(private clientService: ClientService) {
  }

  updateClient() {
    if (this.client.id != null) {
      this.clientService.updateClient(this.client).subscribe();
    }
  }

  cancelUpdate() {
    this.cancel.emit(); // Lorsque l'utilisateur clique sur l'emoji de croix rouge
  }
}
