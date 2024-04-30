import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Technicien} from "../../modeles/TechnicienModele/technicien";

@Injectable({
  providedIn: 'root'
})
export class TechnicienService {
  private apiUrl = '/api/technicien';

  constructor(private httpClient: HttpClient) {
  }

  public getTechniciens(nom?: string, prenom?: string) {
    let params = new HttpParams();
    if (nom) params = params.append('nom', nom);
    if (prenom) params = params.append('prenom', prenom);

    return this.httpClient.get<Technicien[]>(this.apiUrl, { params, observe: 'response' });
  }

  public getTechnicienById(id: number) {
    return this.httpClient.get<Technicien>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  public addTechnicien(technicien: Technicien) {
    return this.httpClient.post<Technicien>(this.apiUrl, technicien, { observe: 'response' });
  }

  public updateTechnicien(id: number, technicien: Technicien) {
    return this.httpClient.put<Technicien>(`${this.apiUrl}/${id}`, technicien, { observe: 'response' });
  }

  public deleteTechnicien(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }
}
