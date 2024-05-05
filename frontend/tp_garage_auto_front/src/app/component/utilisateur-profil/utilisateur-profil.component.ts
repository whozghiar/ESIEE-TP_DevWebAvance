import {Component, OnInit} from '@angular/core';
import {Client} from "../../modeles/ClientModele/client";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {ClientService} from "../../services/ClientService/client.service";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";
import {VehiculeCardComponent} from "../vehicule-card/vehicule-card.component";
import {VehiculeFormComponent} from "../vehicule-form/vehicule-form.component";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {RendezVousCardComponent} from "../rendez-vous-card/rendez-vous-card.component";

@Component({
  selector: 'app-utilisateur-profil',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    LoadingSpinnerComponent,
    NgForOf,
    VehiculeCardComponent,
    VehiculeFormComponent,
    ReactiveFormsModule,
    RendezVousCardComponent
  ],
  templateUrl: './utilisateur-profil.component.html',
  styleUrl: './utilisateur-profil.component.css'
})
export class UtilisateurProfilComponent implements OnInit{

  client!: Client;
  vehicules: Vehicule[] = [];
  rendezVous: any;

  isLoading = true;
  showVehiculeForm: boolean = false;
  editingTelephone: boolean = false;

  telephoneControl = new FormControl('', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]);



  constructor(
    private clientService: ClientService,
    private vehiculeService: VehiculeService,
    private rendezVousService: RendezVousService,
    private authguardService: AuthguardService
  ) {}

  ngOnInit() {
    this.loadClient();
  }

  loadClient() {
    const email = this.authguardService.getEmail();
    this.clientService.getClientByEmail(email).subscribe(response => {
      if (response.status === 200) {
        if (response.body !== null) {
          this.client = response.body;
        }
        this.loadVehicules();
        this.loadRendezVous();
        this.telephoneControl.setValue(this.client.telephone);
        this.isLoading = false;
      }
    });

  }

  loadVehicules() {
    if (this.client) {
      this.vehiculeService.getVehicules(this.client.id).subscribe(response => {
        if (response.status === 200) {
          if (response.body !== null) {
            this.vehicules = response.body;
          }
        }
        if (response.status === 404) {
          console.log('Aucun véhicule trouvé');
        }
      });
    }
  }

  /**
   * Charge les rendez-vous du client
   */
  loadRendezVous() {
    if (this.client) {
      this.rendezVousService.getRendezVous(undefined, undefined, undefined, undefined, this.client.id).subscribe(response => {
        if (response.status === 200) {
          if (response.body !== null) {
            this.rendezVous = response.body;
          }
        }
        if (response.status === 404) {
          console.log('Aucun rendez-vous trouvé');
        }
      });
    }
  }

  updateClient() {
    if (this.telephoneControl.valid) {
      if (this.telephoneControl.value != null) {
        this.client.telephone = this.telephoneControl.value;
      }
      this.clientService.updateClient(this.client).subscribe(response => {
        if (response.status === 202) {
          console.log("Client mis à jour");
        }
      });
    } else {
      console.error("Le numéro de téléphone n'est pas valide")
    }
  }
  addVehicule(vehicule: Vehicule) {
    console.log(vehicule);
    this.vehiculeService.addVehicule(vehicule).subscribe(response => {
      if (response.status === 201) {
        console.log('Vehicule added');
        this.showVehiculeForm = false;
        this.loadVehicules();
      }
    });
  }

  editTelephone() {
    this.editingTelephone = true;
    this.telephoneControl.enable();
  }

  cancelTelephoneUpdate() {
    this.telephoneControl.setValue(this.client.telephone);
    this.editingTelephone = false;
    this.telephoneControl.disable();
  }

  rdvFutur(){
    // Récupère la date actuelle au format dd/mm/yyyy
    const now = new Date();
    const currentDate = `${now.getDate()}/${now.getMonth() + 1}/${now.getFullYear()}`;
    return this.rendezVous.filter((rdv: { date: string; }) => {
      return rdv.date >= currentDate;
    });
  }

  rdvPasse(){
    // Récupère la date actuelle au format dd/mm/yyyy
    const now = new Date();
    const currentDate = `${now.getDate()}/${now.getMonth() + 1}/${now.getFullYear()}`;
    return this.rendezVous.filter((rdv: { date: string; }) => {
      return rdv.date < currentDate;
    });
  }
}
