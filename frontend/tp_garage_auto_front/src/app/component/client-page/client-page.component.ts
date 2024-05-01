import {Component, OnInit} from '@angular/core';
import {Client} from "../../modeles/ClientModele/client";
import {ClientService} from "../../services/ClientService/client.service";
import {ClientCardComponent} from "../client-card/client-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {HttpResponse} from "@angular/common/http";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";

@Component({
  selector: 'app-client-page',
  standalone: true,
  imports: [
    ClientCardComponent,
    NgForOf,
    LoadingSpinnerComponent,
    NgIf
  ],
  templateUrl: './client-page.component.html',
  styleUrl: './client-page.component.css'
})
export class ClientPageComponent implements OnInit {
  clients!: Client[];
  isLoading: boolean = true;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(response => {
      if (response.body !== null) {
        this.clients = response.body;
      }
      this.isLoading = false;
    });
  }

  /**
   * Méthode permettant de supprimer un client depuis le composant client-page
   * L'index du client à supprimer est passé en paramètre
   * On supprime le client du tableau clients à l'index donné
   * @param index
   */
  onClientDeleted(index: number): void {
    this.clients.splice(index, 1);
  }
}
