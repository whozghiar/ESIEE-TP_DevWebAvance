import { Vehicule } from '../VehiculeModele/vehicule';
import { Technicien } from '../TechnicienModele/technicien';

export class RendezVous {
  id?: number;
  date: string;
  typeService: string;
  vehicule?: Vehicule;
  technicien?: Technicien;

  constructor(date: string, typeService: string, vehicule: Vehicule, technicien: Technicien | undefined, id?: number) {
    this.date = date;
    this.typeService = typeService;
    this.vehicule = vehicule;
    this.technicien = technicien;
    this.id = id;
  }
}
