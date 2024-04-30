export class Client {
  id?: number;
  nom: string;
  prenom: string;
  email: string;
  telephone: string;

  constructor(nom: string, prenom: string, email: string, telephone: string, id?: number) {
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.id = id;
  }
}
