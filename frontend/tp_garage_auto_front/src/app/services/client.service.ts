import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private clientArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    setTimeout(() => {
      this.refreshClients();
    }, 1000);
  }

  refreshClients() {
    // Uncomment the following line when your API endpoint is ready
    this.httpClient.get('/api/client').subscribe((clients: any) => {
      this.clientArray = clients;
    });
  }

  getAllClients() {
    return this.clientArray;
  }

  addClient(clientSent: any) {
    let client = {
      prenom: clientSent.prenom,
      nom: clientSent.nom,
      email: clientSent.email,
      telephone: clientSent.telephone,
    };
    // Uncomment the following lines when your API endpoint is ready
    this.httpClient.post('/api/client', client).subscribe(() => {
      this.refreshClients();
    });
    console.log('client added');
  }

  removeClient(id: number) {
    // Uncomment the following lines when your API endpoint is ready
    this.httpClient.delete('/api/client/' + id).subscribe(() => {
      this.refreshClients();
    });
    console.log('client removed');
  }

  getAvailableTechniciansCount() {
    return this.clientArray.filter((client) => client.availability === true)
      .length;
  }
}
