import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";
import {TechnicienService} from "../../services/TechnicienService/technicien.service";
import {Client} from "../../modeles/ClientModele/client";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {Technicien} from "../../modeles/TechnicienModele/technicien";
import {NgForOf, NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";

@Component({
  selector: 'app-rendez-vous-modifier',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgForOf,
    NgIf,
    LoadingSpinnerComponent
  ],
  templateUrl: './rendez-vous-modifier.component.html',
  styleUrl: './rendez-vous-modifier.component.css'
})
export class RendezVousModifierComponent implements OnInit {
  @Input() rendezVous!: RendezVous;
  @Output() cancel = new EventEmitter<void>();

  // Variables pour les listes déroulantes
  vehicules : Vehicule[] = [];
  techniciens: Technicien[] = [];

  // Variables pour les champs du formulaire
  typeControl = new FormControl({value:'',disabled:false}, [Validators.required]);
  dateControl = new FormControl({value:'',disabled:false}, [Validators.required]);
  vehiculeControl = new FormControl({value:'',disabled:false}, [Validators.required]);
  technicienControl = new FormControl({value:'',disabled:false}, [Validators.required]);

  // Création du formulaire
  formGroup = new FormGroup({
    type: this.typeControl,
    date: this.dateControl,
    vehicule: this.vehiculeControl,
    technicien: this.technicienControl
  });

  isLoading : boolean = true;
  isLoadingVehicules: boolean = true;
  isLoadingTechniciens: boolean = true;

  constructor(private rendezVousService: RendezVousService,
              private vehiculeService: VehiculeService,
              private technicienService: TechnicienService,
              protected authguardService: AuthguardService
              ) { }

  ngOnInit() {
    this.loadVehicules();
    this.loadTechniciens();
    this.loadForm();
  }



  /**
   * Charge la liste des véhicules
   */
  loadVehicules(){
    this.vehiculeService.getVehicules().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.vehicules = response.body;
      }
      this.isLoadingVehicules = false;
    });
  }

  /**
   * Charge la liste des techniciens
   */
  loadTechniciens(){
    this.technicienService.getTechniciens().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.techniciens = response.body;
      }
      this.isLoadingTechniciens = false;
    });
  }

  loadForm(){
    this.typeControl.setValue(this.rendezVous.typeService);
    if(this.rendezVous.date != null) {
      let [day, month, year] = this.rendezVous.date.split('/');
      this.dateControl.setValue(year + '-' + month + '-' + day);
    }else{
      this.dateControl.setValue(null);
    }
    this.vehiculeControl.setValue(!this.rendezVous.vehicule?.id ? null : this.rendezVous.vehicule.id.toString());
    this.technicienControl.setValue(!this.rendezVous.technicien?.id ? null : this.rendezVous.technicien.id.toString());
  }

  /**
   * Met à jour le rendez-vous
   */
  updateRendezVous(){
    this.updateRendezVousWithForm();
    this.saveRendezVous();
  }

  /**
   * Met à jour les valeurs du formulaire dans le rendez-vous
   */
  updateRendezVousWithForm(){
    if(this.typeControl.value != null){
      this.rendezVous.typeService = this.typeControl.value;
    }
    if(this.dateControl.value != null){
      // Mettre au format dd/mm/yyyy
      let [year, month, day] = this.dateControl.value.split('-');
      this.rendezVous.date = day + '/' + month + '/' + year;
    }
    let selectedVehicule;
    selectedVehicule = this.vehicules.find(vehicule => {
      return vehicule.id === Number(this.vehiculeControl.value);
    });
    if(selectedVehicule != null){
      this.rendezVous.vehicule = selectedVehicule;
    }
    let selectedTechnicien;
    selectedTechnicien = this.techniciens.find(technicien => {
      return technicien.id === Number(this.technicienControl.value);
    });
    if(selectedTechnicien != null){
      this.rendezVous.technicien = selectedTechnicien;
    }
  }

  /**
   * Sauvegarde les modifications du Rendez-vous
   */
  saveRendezVous(){
    if(this.rendezVous.id != null){
      this.rendezVousService.updateRendezVous(this.rendezVous.id, this.rendezVous).subscribe(response => {
        if(response.status === 202){
          console.log('Rendez-vous updated');
          this.cancelUpdate();
        }
      });
    }
  }

  /**
   * Annule la modification
   */
  cancelUpdate() {
    this.cancel.emit();
  }
}
