export class Technicien {
  id?: number;
  nom: string;
  prenom: string;

  constructor(nom: string, prenom: string, id?: number) {
    this.nom = nom;
    this.prenom = prenom;
    this.id = id;
  }
}
