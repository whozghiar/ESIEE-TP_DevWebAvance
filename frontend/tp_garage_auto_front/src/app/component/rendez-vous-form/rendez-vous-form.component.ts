import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {ClientService} from "../../services/ClientService/client.service";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {TechnicienService} from "../../services/TechnicienService/technicien.service";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";
import {Client} from "../../modeles/ClientModele/client";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";


@Component({
  selector: 'app-rendez-vous-form',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule,
    LoadingSpinnerComponent,
    NgIf
  ],
  templateUrl: './rendez-vous-form.component.html',
  styleUrl: './rendez-vous-form.component.css'
})
export class RendezVousFormComponent implements OnInit{

  @Input() jour!: string;
  @Output() cancel = new EventEmitter<void>();

  rendezVous!: RendezVous;
  servicesTypes: string[] = ['Révision','Contrôle Technique','Réparation'];
  client!: Client;
  vehicules: Vehicule[] = [];

  isClientLoading = true;
  isVehiculeLoading = true;

  // Contrôles de formulaire
  typeServiceControl = new FormControl({value:'',disabled:false}, [Validators.required]);
  dateControl = new FormControl({value:'',disabled:false}, [Validators.required]);
  vehiculeControl = new FormControl({value:'',disabled:false}, [Validators.required]);

  formGroup = new FormGroup({
    typeService: this.typeServiceControl,
    date: this.dateControl,
    vehicule: this.vehiculeControl
  });

  constructor(
    private clientService: ClientService,
    private vehiculeService: VehiculeService,
    private technicienService: TechnicienService,
    private rendezVousService: RendezVousService,
    private authGuardService: AuthguardService
  ) {
  }
  ngOnInit() {
    console.log("Form Rendez-vous init")
    this.loadClients();
  }


  loadClients() {
    const email = this.authGuardService.getEmail();
    this.clientService.getClientByEmail(email).subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.client = response.body;
        this.isClientLoading = false;
        this.loadVehicules();
      }
    });
  }

  loadVehicules() {
    this.vehiculeService.getVehicules(this.client.id).subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.vehicules = response.body;
        this.isVehiculeLoading = false;
        this.loadForm();
      }
    });
  }

  loadForm(){
    this.dateControl.setValue(this.jour);
    this.typeServiceControl.setValue(this.servicesTypes[0]);
    this.vehiculeControl.setValue(!this.vehicules[0]?.id ? null : this.vehicules[0].id.toString());
  }

  onSubmit() {
    if (this.formGroup.valid) {
      const rendezVousToEmit: RendezVous = {
        typeService: this.formGroup.value.typeService || '',
        date: this.formGroup.value.date || '',
      };
      let selectedVehicule = this.vehicules.find(vehicule => {
        return vehicule.id === Number(this.vehiculeControl.value);
      });
      if (selectedVehicule) {
        rendezVousToEmit.vehicule = selectedVehicule;
      }
      console.log(rendezVousToEmit);

      this.rendezVousService.addRendezVous(rendezVousToEmit).subscribe(response => {
        if (response.status === 201) {
          console.log("Rendez-vous ajouté avec succès !");
          this.onCancel();
        }
      });



    }else{
      console.log("Formulaire invalide");
    }
  }

  onCancel() {
    this.cancel.emit();
  }

  setDate(date: string): void {
    this.dateControl.setValue(date);
  }
}
