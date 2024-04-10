import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class VehicleService {
  private vehicleArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    this.refreshVehicles();
  }

  refreshVehicles() {
    this.httpClient
      .get('http://localhost:8080/api/vehicle')
      .subscribe((vehicles: any) => {
        this.vehicleArray = vehicles;
      });
    console.log(this.vehicleArray);
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
    this.httpClient
      .post('http://localhost:8080/api/vehicles', vehicle)
      .subscribe(() => {
        this.refreshVehicles();
      });
  }

  removeVehicle(id: number) {
    this.httpClient
      .delete('http://localhost:8080/api/vehicles/' + id)
      .subscribe(() => {
        this.refreshVehicles();
      });
  }

  getRepairingVehiclesCount() {
    return this.vehicleArray.filter((vehicle) => vehicle.status === false)
      .length;
  }
}
