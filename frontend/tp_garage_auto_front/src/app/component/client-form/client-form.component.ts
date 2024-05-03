import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Client} from "../../modeles/ClientModele/client";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ClientService} from "../../services/ClientService/client.service";

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.css'
})
export class ClientFormComponent implements OnInit{

  @Input() client!: Client;
  @Output() nextStep = new EventEmitter<Client>();

  // Contrôles de formulaire
  nomControl = new FormControl({value:'',disabled:false}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);
  prenomControl = new FormControl({value:'',disabled:false}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);
  emailControl = new FormControl({value:'',disabled:false}, [Validators.required, Validators.email]);
  telephoneControl = new FormControl({value:'',disabled:false}, [Validators.required, Validators.pattern(/^0[1-9][0-9]{8}$/)]);

  clientForm = new FormGroup({
    nom: this.nomControl,
    prenom: this.prenomControl,
    email: this.emailControl,
    telephone: this.telephoneControl
  });

  constructor(
    private clientService: ClientService,
  ) {}

  ngOnInit() {
    console.log("ClientFormComponent initialized");

    // Si on revient sur le formulaire de client, on remplit les champs avec les valeurs précédemment saisies
    if(this.client){
      this.nomControl.setValue(this.client.nom);
      this.prenomControl.setValue(this.client.prenom);
      this.emailControl.setValue(this.client.email);
      this.telephoneControl.setValue(this.client.telephone);
    }
  }

  onSubmit(){
    if(this.clientForm.valid){
      const ClientToEmit: Client = {
        nom: this.clientForm.value.nom || '',
        prenom: this.clientForm.value.prenom || '',
        email: this.clientForm.value.email || '',
        telephone: this.clientForm.value.telephone || ''
      };
      this.nextStep.emit(ClientToEmit);
    }
  }
}
