import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {
  private technicianArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    this.refreshTechnicians();
  }

  refreshTechnicians() {
    // Uncomment the following line when your API endpoint is ready
    // this.httpClient.get("/api/technician").subscribe((technicians: any) => {
    //   this.technicianArray = technicians;
    // });
  }

  getAllTechnicians() {
    return this.technicianArray;
  }

  addTechnician(technicianSent: any) {
    let technician = {
      name: technicianSent.name,
      surname: technicianSent.surname,
      availability: technicianSent.availability,
      avatar: technicianSent.avatar,
    };
    // Uncomment the following lines when your API endpoint is ready
    // this.httpClient.post("/api/technicians", technician).subscribe(() => {
    //   this.refreshTechnicians();
    // })
  }

  removeTechnician(id: number) {
    // Uncomment the following lines when your API endpoint is ready
    // this.httpClient.delete("/api/technicians/" + id).subscribe(() => {
    //   this.refreshTechnicians();
    // })
  }
}
