import {Client} from "../ClientModele/client";

export class Vehicule {
  marque: string;
  modele: string;
  immatriculation: string;
  annee: number;
  client?: Client;

  constructor(marque: string, modele: string, immatriculation: string, annee: number, client: Client) {
    this.marque = marque;
    this.modele = modele;
    this.immatriculation = immatriculation;
    this.annee = annee;
    this.client = client;
  }
}
