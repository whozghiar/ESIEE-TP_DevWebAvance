import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class VehicleService {
  private vehicleArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    this.refreshVehicles();
    console.log('constructor vehicles');
  }

  refreshVehicles() {
    this.httpClient.get('/api/vehicule').subscribe((vehicles: any) => {
      console.log(vehicles, 'get vehicles requete envoyée');
      this.vehicleArray = vehicles;
    });
    console.log(this.vehicleArray + 'refresh vehicles');
  }

  getAllVehicles() {
    console.log(this.vehicleArray, 'getall vehicles');
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
    console.log(vehicle, 'vehicle ajout');
    this.httpClient.post('/api/vehicule', vehicle).subscribe(() => {
      this.refreshVehicles();
      //console.log(vehicle, 'vehicle ajout requete envoyée" ');
    });
  }

  removeVehicle(id: number) {
    this.httpClient.delete('/api/vehicule/' + id).subscribe(() => {
      console.log('delete requete envoyée ');
      this.refreshVehicles();
    });
    console.log('fct delete vehicle');
  }

  getRepairingVehiclesCount() {
    return this.vehicleArray.filter((vehicle) => vehicle.status === false)
      .length;
  }
}
