import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class VehicleService {
  private vehicleArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    console.log('Vehicle service constructor');
    this.refreshVehicles();
  }

  refreshVehicles() {
    this.httpClient.get('/api/vehicule').subscribe((vehicles: any) => {
      console.log(vehicles, 'vehicles');
      this.vehicleArray = vehicles;
    });
    console.log(this.vehicleArray + 'vehicle array');
  }

  getAllVehicles() {
    return this.vehicleArray;
  }

  addVehicle(vehicleSent: any) {
    let vehicle = {
      client: vehicleSent.client,
      modele: vehicleSent.model,
      marque: vehicleSent.brand,
      annee: vehicleSent.year,
      immatriculation: vehicleSent.plateNumber,
      rendezVous: vehicleSent.appointmentsVehicle,
    };
    this.httpClient.post('/api/vehicules', vehicle).subscribe(() => {
      this.refreshVehicles();
    });
  }

  removeVehicle(id: number) {
    this.httpClient.delete('/api/vehicules/' + id).subscribe(() => {
      this.refreshVehicles();
    });
  }

  getRepairingVehiclesCount() {
    return this.vehicleArray.filter((vehicle) => vehicle.status === false)
      .length;
  }
}
