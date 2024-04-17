import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TechnicianService {
  private technicianArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    setTimeout(() => {
      this.refreshTechnicians();
    }, 1000);
  }

  refreshTechnicians() {
    // Uncomment the following line when your API endpoint is ready
    this.httpClient.get('/api/technicien').subscribe((technicians: any) => {
      this.technicianArray = technicians;
    });
  }

  getAllTechnicians() {
    return this.technicianArray;
  }

  addTechnician(technicianSent: any) {
    let technician = {
      nom: technicianSent.nom,
      prenom: technicianSent.prenom,
      /*availability: true,
      avatar: 'https://www.w3schools.com/howto/img_avatar.png',*/
    };
    // Uncomment the following lines when your API endpoint is ready
    this.httpClient.post('/api/technicien', technician).subscribe(() => {
      this.refreshTechnicians();
    });
  }

  removeTechnician(id: number) {
    // Uncomment the following lines when your API endpoint is ready
    this.httpClient.delete('/api/technicien/' + id).subscribe(() => {
      this.refreshTechnicians();
    });
  }

  getAvailableTechniciansCount() {
    return this.technicianArray.filter(
      (technician) => technician.availability === true,
    ).length;
  }
}
