import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";

@Injectable({
  providedIn: 'root'
})
export class RendezVousService {

  private apiUrl = '/api/rendez-vous';

  constructor(private httpClient: HttpClient) {}

  public getRendezVous(date?: string, typeService?: string, vehicule_id?: number, technicien_id?: number, clientId?: number) {
    let params = new HttpParams();
    if (date) params = params.append('date', date);
    if (typeService) params = params.append('typeService', typeService);
    if (vehicule_id) params = params.append('vehicule_id', vehicule_id.toString());
    if (technicien_id) params = params.append('technicien_id', technicien_id.toString());
    if (clientId) params = params.append('client_id', clientId.toString());

    return this.httpClient.get<RendezVous[]>(this.apiUrl, { params, observe: 'response' });
  }

  public getRendezVousById(id: number) {
    return this.httpClient.get<RendezVous>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  public addRendezVous(rendezVous: RendezVous) {
    return this.httpClient.post<RendezVous>(this.apiUrl, rendezVous, { observe: 'response' });
  }

  public updateRendezVous(id: number, rendezVous: RendezVous) {
    return this.httpClient.put<RendezVous>(`${this.apiUrl}/${id}`, rendezVous, { observe: 'response' });
  }

  public deleteRendezVous(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }
}
