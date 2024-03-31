import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private vehicleArray: any[] = [];

  constructor(private httpClient: HttpClient) {
    this.refreshVehicles();
  }

  refreshVehicles() {
    /*this.httpClient.get("/api/vehicle").subscribe((vehicles: any) => {
      this.vehicleArray = vehicles;
    });*/
  }

  getAllVehicle() {
    return this.vehicleArray;
  }

  addVehicle(vehicleSent: any) {
    let vehicle = {
      client: vehicleSent.client,
      model: vehicleSent.model,
      issue: vehicleSent.issue,
      technician: vehicleSent.technician,
    };
    /*this.httpClient.post("/api/vehicles", vehicle).subscribe(() => {
      this.refreshVehicles();
    })*/
  }



  removeVehicle(id: number) {
    //this.httpClient.delete("/api/vehicles/" + id).subscribe(() => {
      //this.refreshVehicles();
    //})
  }

  getRepairingVehiclesCount() {
    return this.vehicleArray.filter((vehicle) => vehicle.status === false).length;
  }

}

