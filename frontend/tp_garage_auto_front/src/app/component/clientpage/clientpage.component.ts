import { Component } from '@angular/core';
import { ClientformComponent } from '../clientform/clientform.component';
import { NgForOf } from '@angular/common';
import { ClientComponent } from '../client/client.component';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-clientpage',
  standalone: true,
  imports: [ClientformComponent, NgForOf, ClientComponent],
  templateUrl: './clientpage.component.html',
  styleUrl: './clientpage.component.css',
})
export class ClientpageComponent {
  clients: any = [];
  client: any = {};

  constructor(private clientService: ClientService) {
    this.clients = this.clientService.getAllClients();
  }
  handleDeletion(id: number) {
    this.clientService.removeClient(id);
    console.log(id, typeof id);
    this.clients = this.clients.filter((client: any) => client.id !== id);
  }
  handleAddition(client: any) {
    this.clientService.addClient(client);
    this.clients.push(client);
  }
}
