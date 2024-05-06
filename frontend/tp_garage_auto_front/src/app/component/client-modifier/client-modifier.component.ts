import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Client} from "../../modeles/ClientModele/client";
import {ClientService} from "../../services/ClientService/client.service";
import {NgIf} from "@angular/common";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-client-modifier',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './client-modifier.component.html',
  styleUrl: './client-modifier.component.css'
})
export class ClientModifierComponent implements OnInit{
  @Input() client!: Client;
  @Output() cancel = new EventEmitter<void>(); // Ajoutez cette ligne

  // Contrôles de formulaire
  nomControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);
  prenomControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);
  emailControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.email]);
  telephoneControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^0[1-9][0-9]{8}$/)]);

  editingNom = false;
  editingPrenom = false;
  editingEmail = false;
  editingTelephone = false;

  formGroup = new FormGroup({
    nom: this.nomControl,
    prenom: this.prenomControl,
    email: this.emailControl,
    telephone: this.telephoneControl
  });

  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.nomControl.setValue(this.client.nom);
    this.prenomControl.setValue(this.client.prenom);
    this.emailControl.setValue(this.client.email);
    this.telephoneControl.setValue(this.client.telephone);
  }
  updateClient() {
    if (this.client.id != null) {
      if (this.nomControl.value != null) {
        this.client.nom = this.nomControl.value;
      }
      if (this.prenomControl.value != null) {
        this.client.prenom = this.prenomControl.value;
      }
      if (this.emailControl.value != null) {
        this.client.email = this.emailControl.value;
      }
      if (this.telephoneControl.value != null) {
        this.client.telephone = this.telephoneControl.value;
      }
      this.clientService.updateClient(this.client).subscribe(response => {
        if (response.status === 202) {
          this.cancel.emit(); // Lorsque la mise à jour est terminée, on émet l'événement cancel
        }
      });
    }
  }

  cancelUpdate() {
    this.cancel.emit(); // Lorsque l'utilisateur clique sur l'emoji de croix rouge
  }

  editNom() {
    this.editingNom = true;
    this.nomControl.enable();
  }

  cancelNomUpdate() {
    console.log("Cancel nom update");
    this.nomControl.setValue(this.client.nom);
    this.editingNom = false;
    this.nomControl.disable();
  }

  editPrenom() {
    this.editingPrenom = true;
    this.prenomControl.enable();
  }

  cancelPrenomUpdate() {
    console.log("Cancel prenom update");
    this.prenomControl.setValue(this.client.prenom);
    this.editingPrenom = false;
    this.prenomControl.disable();
  }

  editEmail() {
    this.editingEmail = true;
    this.emailControl.enable();
  }

  cancelEmailUpdate() {
    console.log("Cancel email update");
    this.emailControl.setValue(this.client.email);
    this.editingEmail = false;
    this.emailControl.disable();
  }

  editTelephone() {
    this.editingTelephone = true;
    this.telephoneControl.enable();
  }

  cancelTelephoneUpdate() {
    console.log("Cancel telephone update");
    this.telephoneControl.setValue(this.client.telephone);
    this.editingTelephone = false;
    this.telephoneControl.disable();
  }
}
