import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {Client} from "../../modeles/ClientModele/client";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from "@angular/forms";
import {NgIf} from "@angular/common";
import {VehiculeService} from "../../services/VehiculesService/vehicule.service";

@Component({
  selector: 'app-vehicule-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './vehicule-form.component.html',
  styleUrl: './vehicule-form.component.css'
})
export class VehiculeFormComponent implements OnInit{

  @Input() client!: Client;
  @Output() cancel = new EventEmitter<void>();
  @Output() vehiculeToEmit = new EventEmitter<Vehicule>();

  // Contrôles de formulaire
  marqueControl = new FormControl({value:'Citroën',disabled:false}, [Validators.required]);
  modeleControl = new FormControl({value:'C1',disabled:false}, [Validators.required]);
  anneeControl = new FormControl({value:'2021',disabled:false}, [Validators.required,this.yearValidator]);
  immatriculationControl = new FormControl({value:'AB-444-DC',disabled:false}, [Validators.required, Validators.pattern(/^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$/)]);

  vehiculeForm = new FormGroup({
    marque: this.marqueControl,
    modele: this.modeleControl,
    annee: this.anneeControl,
    immatriculation: this.immatriculationControl
  });

  constructor(){
  }

  ngOnInit() {
  }


  onSubmit(){
    if(this.vehiculeForm.valid){
      const vehiculeToEmit: Vehicule = {
        marque: this.vehiculeForm.value.marque || '',
        modele: this.vehiculeForm.value.modele || '',
        annee: Number(this.vehiculeForm.value.annee) || 0,
        immatriculation: this.vehiculeForm.value.immatriculation || '',
        client: this.client
      };

      this.vehiculeToEmit.emit(vehiculeToEmit);
    }
  }

  cancelForm() {
    this.cancel.emit();
  }

  yearValidator(control: AbstractControl): ValidationErrors | null {
    const currentYear = new Date().getFullYear();
    const inputYear = Number(control.value);
    if (inputYear > currentYear) {
      return { 'yearInvalid': true };
    }
    return null;
  }

}
