export class Technicien {
  id?: number;
  nom: string;
  prenom: string;
  email?: string;

  constructor(nom: string, prenom: string, email:string, id?: number) {
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.id = id;
  }
}
