import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";

@Injectable({
  providedIn: 'root'
})
export class VehiculeService {
  private apiUrl = '/api/vehicule';

  constructor(private httpClient: HttpClient) {}

  public getVehicules(client_id?: number, marque?: string, modele?: string, annee?: string, immatriculation?: string) {
    let params = new HttpParams();
    if (client_id) params = params.append('client_id', client_id);
    if (marque) params = params.append('marque', marque);
    if (modele) params = params.append('modele', modele);
    if (annee) params = params.append('annee', annee);
    if (immatriculation) params = params.append('immatriculation', immatriculation);

    return this.httpClient.get<Vehicule[]>(this.apiUrl, { params, observe: 'response' });
  }

  public getVehiculeById(id: number) {
    return this.httpClient.get<Vehicule>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  public addVehicule(vehicule: Vehicule) {
    return this.httpClient.post<Vehicule>(this.apiUrl, vehicule, { observe: 'response' });
  }

  public updateVehicule(id: number, vehicule: Vehicule) {
    return this.httpClient.put<Vehicule>(`${this.apiUrl}/${id}`, vehicule, { observe: 'response' });
  }

  public deleteVehicule(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }
}
