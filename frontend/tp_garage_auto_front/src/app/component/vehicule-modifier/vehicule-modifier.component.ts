import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {NgForOf, NgIf} from "@angular/common";
import {Client} from "../../modeles/ClientModele/client";
import {ClientService} from "../../services/ClientService/client.service";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";

@Component({
  selector: 'app-vehicule-modifier',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    FormsModule,
    NgForOf,
    LoadingSpinnerComponent
  ],
  templateUrl: './vehicule-modifier.component.html',
  styleUrl: './vehicule-modifier.component.css'
})
export class VehiculeModifierComponent implements OnInit{

  clients : Client[] = [];
  @Input() vehicule!: Vehicule;
  @Output() cancel = new EventEmitter<void>();

  marqueControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  modeleControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  immatriculationControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern('^(?:\\d{4} \\w{2} \\d{2}|\\w{2}-\\d{3}-\\w{2})$')]);  anneeControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  clientControl = new FormControl({value:'',disabled:true}, [Validators.required]);

  editingMarque = false;
  editingModele = false;
  editingImmatriculation = false;
  editingAnnee = false;

  editingClient = false;

  isLoading = true;

  formGroup = new FormGroup({
    marque: this.marqueControl,
    modele: this.modeleControl,
    immatriculation: this.immatriculationControl,
    annee: this.anneeControl,

    client: this.clientControl
  });

  constructor(
    private vehiculeService: VehiculeService,
    private clientService: ClientService,
    protected authguardService: AuthguardService
    ) {  }

  ngOnInit() {
    // Récupération de la liste des clients
    this.clientService.getClients().subscribe(response => {
      if (response.body !== null) {
        this.clients = response.body;
      }
      this.isLoading = false;
    });


    this.marqueControl.setValue(this.vehicule.marque);
    this.modeleControl.setValue(this.vehicule.modele);
    this.immatriculationControl.setValue(this.vehicule.immatriculation);
    this.anneeControl.setValue(this.vehicule.annee.toString());

    this.clientControl.setValue(!this.vehicule.client?.id ? null : this.vehicule.client.id.toString()); // Peut être placer dans le if
  }

  updateVehicule() {
    let selectedClient;
    if (this.vehicule.id != null) {
      if (this.marqueControl.value != null) {
        this.vehicule.marque = this.marqueControl.value;
      }
      if (this.modeleControl.value != null) {
        this.vehicule.modele = this.modeleControl.value;
      }
      if (this.immatriculationControl.value != null) {
        this.vehicule.immatriculation = this.immatriculationControl.value;
      }
      if (this.anneeControl.value != null) {
        this.vehicule.annee = Number(this.anneeControl.value);
      }
      selectedClient = this.clients.find(client => {
        return client.id === Number(this.clientControl.value);
      });
      if (selectedClient != null) {
        this.vehicule.client = selectedClient;
      }

      console.log(this.vehicule);
      this.vehiculeService.updateVehicule(this.vehicule.id, this.vehicule).subscribe(response => {
        if (response.status === 202) {
          this.cancel.emit();
        }
      });
    }
  }

  cancelUpdate() {
    this.cancel.emit();
  }

  editMarque() {
    this.editingMarque = true;
    this.marqueControl.enable();
  }

  cancelMarqueUpdate() {
    this.marqueControl.setValue(this.vehicule.marque);
    this.editingMarque = false;
    this.marqueControl.disable();
  }

  editModele() {
    this.editingModele = true;
    this.modeleControl.enable();
  }

  cancelModeleUpdate() {
    this.modeleControl.setValue(this.vehicule.modele);
    this.editingModele = false;
    this.modeleControl.disable();
  }

  editImmatriculation() {
    this.editingImmatriculation = true;
    this.immatriculationControl.enable();
  }

  cancelImmatriculationUpdate() {
    this.immatriculationControl.setValue(this.vehicule.immatriculation);
    this.editingImmatriculation = false;
    this.immatriculationControl.disable();
  }

  editAnnee() {
    this.editingAnnee = true;
    this.anneeControl.enable();
  }

  cancelAnneeUpdate() {
    this.anneeControl.setValue(this.vehicule.annee.toString());
    this.editingAnnee = false;
    this.anneeControl.disable();
  }

  editClient() {
    this.editingClient = true;
    this.clientControl.enable();
  }

  cancelClientUpdate() {
    this.clientControl.setValue(!this.vehicule.client?.id ? null : this.vehicule.client.id.toString());
    this.editingClient = false;
    this.clientControl.disable();
  }
}
