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

  vehicules : Vehicule[] = [];
  techniciens: Technicien[] = [];

  typeControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  dateControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  vehiculeControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  technicienControl = new FormControl({value:'',disabled:true}, [Validators.required]);

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
              private technicienService: TechnicienService) { }

  ngOnInit() {
    console.log(this.rendezVous);
    this.vehiculeService.getVehicules().subscribe(response => {
      if (response.body !== null) {
        this.vehicules = response.body;
        this.vehiculeControl.setValue(!this.rendezVous.vehicule?.id ? null : this.rendezVous.vehicule.id.toString());
        console.log("Vehicules1 : " + this.vehicules);
      }
      this.isLoadingVehicules = false;
      this.checkLoading();
    });

    this.technicienService.getTechniciens().subscribe(response => {
      if (response.body !== null) {
        this.techniciens = response.body;
        this.technicienControl.setValue(!this.rendezVous.technicien?.id ? null : this.rendezVous.technicien.id.toString());
        console.log("Techniciens1 : " + this.techniciens);
      }
      this.isLoadingTechniciens = false;
      this.checkLoading();
    });

    this.typeControl.setValue(this.rendezVous.typeService);
    this.dateControl.setValue(this.rendezVous.date.toString());
  }

  updateRendezVous(){
    let selectedVehicule;
    let selectedTechnicien;

    if(this.rendezVous.id != null){
      if(this.typeControl.value != null){
        this.rendezVous.typeService = this.typeControl.value;
      }
      if(this.dateControl.value != null){
        this.rendezVous.date = this.dateControl.value;
      }

      selectedVehicule = this.vehicules.find(vehicule => {
        return vehicule.id === Number(this.vehiculeControl.value);
      });

      if(selectedVehicule != null){
        this.rendezVous.vehicule = selectedVehicule;
      }

      selectedTechnicien = this.techniciens.find(technicien => {
        return technicien.id === Number(this.technicienControl.value);
      });

      if(selectedTechnicien != null){
        this.rendezVous.technicien = selectedTechnicien;
      }

      this.rendezVousService.updateRendezVous(this.rendezVous.id, this.rendezVous).subscribe(response => {
        if (response.status === 202) {
          this.cancel.emit();
        }
      });
    }
  }

  cancelUpdate() {
    this.cancel.emit();
  }

  checkLoading() {
    if(!this.isLoadingVehicules && !this.isLoadingTechniciens) {
      this.isLoading = false;
      console.log("Loading done !");
    }
  }

}
