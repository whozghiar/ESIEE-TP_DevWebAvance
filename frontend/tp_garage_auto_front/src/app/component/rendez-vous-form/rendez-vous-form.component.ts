import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {Vehicule} from "../../modeles/VehiculeModele/vehicule";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {RendezVousCalendrierComponent} from "../rendez-vous-calendrier/rendez-vous-calendrier.component";
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";

@Component({
  selector: 'app-rendez-vous-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    RendezVousCalendrierComponent,
    LoadingSpinnerComponent
  ],
  templateUrl: './rendez-vous-form.component.html',
  styleUrl: './rendez-vous-form.component.css'
})
export class RendezVousFormComponent implements OnInit{

  @Output() previousStep = new EventEmitter<Vehicule>();
  @Input() vehicule!: Vehicule;

  isLoading: boolean = false;

  // Contrôles de formulaire
  dateControl = new FormControl({value:'',disabled:false}, [Validators.required,Validators.pattern(/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[012])\/([12][0-9]{3})$/)]); // Validators.pattern(/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[012])\/([12][0-9]{3})$/)]
  typeServiceControl = new FormControl({value:'Révision',disabled:false}, [Validators.required]);


  rendezVousForm = new FormGroup({
    date: this.dateControl,
    typeService: this.typeServiceControl
  });

  constructor(
    private rendezVousService: RendezVousService,
  ){}

  ngOnInit() {
    console.log('Vehicule', this.vehicule)
  }

  onSubmit(){
    if(this.rendezVousForm.valid){
      const rendezVous : RendezVous = {
        date: this.rendezVousForm.value.date || '',
        typeService: this.rendezVousForm.value.typeService || '',
        vehicule: this.vehicule
      };
      this.rendezVousService.addRendezVous(rendezVous).subscribe(response => {
        this.isLoading = true;
        if(response.status === 201){
          console.log('Rendez-vous créé');
          this.isLoading = false;
        }
      })
    }
  }

  onPrevious(){
    this.previousStep.emit();
  }


  onDateSelected(date: string) {
    this.dateControl.setValue(date);
  }
}
