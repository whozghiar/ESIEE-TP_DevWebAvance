import {Client} from "../ClientModele/client";

export class Vehicule {
  id?: number;
  marque: string;
  modele: string;
  immatriculation: string;
  annee: number;
  client?: Client;

  constructor(marque: string, modele: string, immatriculation: string, annee: number, client: Client, id?: number) {
    this.marque = marque;
    this.modele = modele;
    this.immatriculation = immatriculation;
    this.annee = annee;
    this.client = client;
    this.id = id;
  }
}
