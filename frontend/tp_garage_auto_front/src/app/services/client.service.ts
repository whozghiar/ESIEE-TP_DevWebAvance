import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private clientArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    this.refreshClients();
  }

  refreshClients() {
    // Uncomment the following line when your API endpoint is ready
    // this.httpClient.get("/api/technician").subscribe((technicians: any) => {
    //   this.technicianArray = technicians;
    // });
  }

  getAllClients() {
    return this.clientArray;
  }

  addClient(clientSent: any) {
    let client = {
      name: clientSent.name,
      surname: clientSent.surname,
      availability: clientSent.availability,
      avatar: clientSent.avatar,
    };
    // Uncomment the following lines when your API endpoint is ready
    // this.httpClient.post("/api/technicians", technician).subscribe(() => {
    //   this.refreshTechnicians();
    // })
  }

  removeClient(id: number) {
    // Uncomment the following lines when your API endpoint is ready
    // this.httpClient.delete("/api/client/" + id).subscribe(() => {
    //   this.refreshClients();
    // })
  }

  getAvailableTechniciansCount() {
    return this.clientArray.filter((client) => client.availability === true)
      .length;
  }
}
