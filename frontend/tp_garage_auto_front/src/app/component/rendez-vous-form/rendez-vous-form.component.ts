import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-rendez-vous-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './rendez-vous-form.component.html',
  styleUrl: './rendez-vous-form.component.css'
})
export class RendezVousFormComponent {
  step: number = 1;
  clientForm : FormGroup;
  vehiculeForm : FormGroup;
  rendezVousForm : FormGroup;

  constructor(
    private fb: FormBuilder
  ) {
    this.clientForm = this.fb.group({
      nom: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]],
      prenom: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', [Validators.required, Validators.pattern(/^0[1-9][0-9]{8}$/)]]
    });

    const currentYear = new Date().getFullYear(); // Récupère l'année actuelle

    this.vehiculeForm = this.fb.group({
      marque: ['', Validators.required],
      modele: ['', Validators.required],
      immatriculation: ['', [Validators.required, Validators.pattern(/^[A-Z]{2}-\d{3}-[A-Z]{2}$/)]],
      annee: ['', [Validators.required, Validators.max(currentYear)]]
    });

    this.rendezVousForm = this.fb.group({
      date: ['', [Validators.required, this.dateValidator]],
      typeService: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]]
    });
  }

  dateValidator(control: FormGroup): {[key: string]: any} | null {
    const dateControl = control.get('date');
    const selectedDate = new Date(dateControl?.value);
    const now = new Date();
    if (selectedDate < now) {
      return { 'dateValidator': true };
    }
    return null;
  }

  nextStep() {
    if (this.step === 1 && this.clientForm.valid) {
      this.step++;
    } else if (this.step === 2 && this.vehiculeForm.valid) {
      this.step++;
    } else if (this.step === 3 && this.rendezVousForm.valid) {
      this.submitRendezVous();
    }
  }

  previousStep() {
    if(this.step > 1) {
      this.step--;
    }
  }

  submitRendezVous() {
    const data = {
      client: this.clientForm.value,
      vehicule: this.vehiculeForm.value,
      rendezVous: this.rendezVousForm.value
    };

    console.log(data);
  }
}
