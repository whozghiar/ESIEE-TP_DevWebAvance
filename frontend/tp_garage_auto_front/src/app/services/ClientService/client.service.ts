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

  public getClients (nom?: string, prenom?: string, email?: string, telephone?: string): Observable<HttpResponse<Client[]>> {
    let params = new HttpParams();
    if (nom) params = params.append('nom', nom);
    if (prenom) params = params.append('prenom', prenom);
    if (email) params = params.append('email', email);
    if (telephone) params = params.append('telephone', telephone);

    return this.httpClient.get<Client[]>(this.apiUrl, { params, observe: 'response' });
  }

  public getClientById(id: number) {
    return this.httpClient.get<Client>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }

  public addClient(client: Client) {
    return this.httpClient.post<Client>(this.apiUrl, client, { observe: 'response' });
  }

  public updateClient(client: Client) {
    return this.httpClient.put<Client>(`${this.apiUrl}/${client.id}`, client, { observe: 'response' });
  }

  public deleteClient(id: number) {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`, { observe: 'response' });
  }
}
