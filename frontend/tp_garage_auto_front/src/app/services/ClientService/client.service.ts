import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Client} from "../../modeles/ClientModele/client";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private clientArray: any[] = [];
  private apiUrl = '/api/client';

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Récupère les clients
   * @param nom - filtre par nom
   * @param prenom - filtre par prénom
   * @param telephone - filtre par téléphone
   */
  public getClients (nom?: string, prenom?: string, telephone?: string): Observable<HttpResponse<Client[]>> {
    let params = new HttpParams();
    if (nom) params = params.append('nom', nom);
    if (prenom) params = params.append('prenom', prenom);
    if (telephone) params = params.append('telephone', telephone);

    return this.httpClient.get<Client[]>(this.apiUrl, { params, observe: 'response' });
  }

  /**
   * Récupère un client par son id
   * @param id
   */
  public getClientById(id: number) {
    return this.httpClient.get<Client>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  /**
   * Récupère un client par son email
   * @param email
   */
  public getClientByEmail(email: string) {
    return this.httpClient.get<Client>(`${this.apiUrl}/email/${email}`, { observe: 'response' });
  }

  /**
   * Ajoute un client
   * @param client
   */
  public addClient(client: Client) {
    return this.httpClient.post<Client>(this.apiUrl, client, { observe: 'response' });
  }

  /**
   * Met à jour un client
   * @param client
   */
  public updateClient(client: Client) {
    return this.httpClient.put<Client>(`${this.apiUrl}/${client.id}`, client, { observe: 'response' });
  }

  /**
   * Supprime un client
   * @param id
   */
  public deleteClient(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }
}
