import { Vehicule } from '../VehiculeModele/vehicule';
import { Technicien } from '../TechnicienModele/technicien';

export class RendezVous {
  date: string;
  typeService: string;
  vehicule?: Vehicule;
  technicien?: Technicien;

  constructor(date: string, typeService: string, vehicule: Vehicule, technicien: Technicien) {
    this.date = date;
    this.typeService = typeService;
    this.vehicule = vehicule;
    this.technicien = technicien;
  }
}
