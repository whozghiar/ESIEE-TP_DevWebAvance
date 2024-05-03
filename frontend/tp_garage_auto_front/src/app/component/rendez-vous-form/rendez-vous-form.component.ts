import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {RendezVousCalendrierComponent} from "../rendez-vous-calendrier/rendez-vous-calendrier.component";

@Component({
  selector: 'app-rendez-vous-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    RendezVousCalendrierComponent
  ],
  templateUrl: './rendez-vous-form.component.html',
  styleUrl: './rendez-vous-form.component.css'
})
export class RendezVousFormComponent implements OnInit{

  @Output() previousStep = new EventEmitter<Vehicule>();
  @Input() vehicule!: Vehicule;

  // Contrôles de formulaire
  dateControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  typeServiceControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);


  rendezVousForm = new FormGroup({
    date: this.dateControl,
    typeService: this.typeServiceControl
  });

  constructor(
    private rendezVousService: RendezVousService,
  ){}

  ngOnInit() {
    console.log("RendezVousFormComponent initialized");
    console.log('Vehicule', this.vehicule)
  }

  onSubmit(){
    if(this.rendezVousForm.valid){
      const rendezVous = {
        date: this.rendezVousForm.value.date || '',
        typeService: this.rendezVousForm.value.typeService || ''
      };
      console.log(rendezVous);
    }
  }

  onPrevious(){
    this.previousStep.emit();
  }


  onDateSelected(date: string) {
    console.log('Received dateSelected event', date);
    this.dateControl.setValue(date);
  }
}
