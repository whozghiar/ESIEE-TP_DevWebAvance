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

  // Variables pour les champs du formulaire
  marqueControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  modeleControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  immatriculationControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern('^(?:\\d{4} \\w{2} \\d{2}|\\w{2}-\\d{3}-\\w{2})$')]);  anneeControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  clientControl = new FormControl({value:'',disabled:true}, [Validators.required]);

  // Variables pour l'édition des champs
  editingMarque = false;
  editingModele = false;
  editingImmatriculation = false;
  editingAnnee = false;
  editingClient = false;

  isLoading = true;

  // Création du formulaire
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

    this.loadClients();
    this.loadForm();
  }

  /**
   * Charge les valeurs du véhicule dans le formulaire
   */
  loadForm(){
    this.marqueControl.setValue(this.vehicule.marque);
    this.modeleControl.setValue(this.vehicule.modele);
    this.immatriculationControl.setValue(this.vehicule.immatriculation);
    this.anneeControl.setValue(this.vehicule.annee.toString());
    this.clientControl.setValue(!this.vehicule.client?.id ? null : this.vehicule.client.id.toString());
  }

  /**
   * Charge la liste des clients
   */
  loadClients(){
    this.clientService.getClients().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.clients = response.body;
      }
      this.isLoading = false;
    });
  }

  /**
   * Met à jour le véhicule
   * Si l'id du véhicule est défini, on met à jour le véhicule
   */
  updateVehicule() {
      this.updateVehiculeWithForm();
      this.saveVehicule();
  }

  /**
   * Met à jour les valeurs du formulaire dans le véhicule
   */
  updateVehiculeWithForm(){
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
    let selectedClient;
    selectedClient = this.clients.find(client => {
      return client.id === Number(this.clientControl.value);
    });
    if (selectedClient != null) {
      this.vehicule.client = selectedClient;
    }
  }


  /**
   * Sauvegarde les modifications du véhicule
   */
  saveVehicule(){
    console.log('Véhicule à mettre à jour : ',this.vehicule);
    if(this.vehicule.id != null){
      this.vehiculeService.updateVehicule(this.vehicule.id, this.vehicule).subscribe(response => {
        if(response.status === 202){
          console.log('Véhicule mis à jour');
          this.cancelUpdate();
        }
      });
    }
  }

  /**
   * On annule la modification
   * @emits cancel - émet un événement pour annuler la modification (récupéré par le parent)
   */
  cancelUpdate() {
    this.cancel.emit();
  }

  /**
   * Active le mode édition pour le champ marque
   */
  editMarque() {
    this.editingMarque = true;
    this.marqueControl.enable();
  }

  /**
   * Annule la modification du champ marque et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelMarqueUpdate() {
    this.marqueControl.setValue(this.vehicule.marque);
    this.editingMarque = false;
    this.marqueControl.disable();
  }

  /**
   * Active le mode édition pour le champ modèle
   */
  editModele() {
    this.editingModele = true;
    this.modeleControl.enable();
  }

  /**
   * Annule la modification du champ modèle et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelModeleUpdate() {
    this.modeleControl.setValue(this.vehicule.modele);
    this.editingModele = false;
    this.modeleControl.disable();
  }

  /**
   * Active le mode édition pour le champ immatriculation
   */
  editImmatriculation() {
    this.editingImmatriculation = true;
    this.immatriculationControl.enable();
  }

  /**
   * Annule la modification du champ immatriculation et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelImmatriculationUpdate() {
    this.immatriculationControl.setValue(this.vehicule.immatriculation);
    this.editingImmatriculation = false;
    this.immatriculationControl.disable();
  }

  /**
   * Active le mode édition pour le champ année
   * Désactive le mode édition
   */
  editAnnee() {
    this.editingAnnee = true;
    this.anneeControl.enable();
  }

  /**
   * Annule la modification du champ année et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelAnneeUpdate() {
    this.anneeControl.setValue(this.vehicule.annee.toString());
    this.editingAnnee = false;
    this.anneeControl.disable();
  }

  /**
   * Active le mode édition pour le champ client
   */
  editClient() {
    this.editingClient = true;
    this.clientControl.enable();
  }

  /**
   * Annule la modification du champ client et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelClientUpdate() {
    this.clientControl.setValue(!this.vehicule.client?.id ? null : this.vehicule.client.id.toString());
    this.editingClient = false;
    this.clientControl.disable();
  }
}
