import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Technicien} from "../../modeles/TechnicienModele/technicien";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {TechnicienService} from "../../services/TechnicienService/technicien.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-technicien-modifier',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgIf
  ],
  templateUrl: './technicien-modifier.component.html',
  styleUrl: './technicien-modifier.component.css'
})
export class TechnicienModifierComponent implements OnInit{
  @Input() technicien!: Technicien;
  @Output() cancel = new EventEmitter<void>();

  nomControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);
  prenomControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.pattern(/^[a-zA-Z]*$/)]);

  editingNom = false;
  editingPrenom = false;

  formGroup = new FormGroup({
    nom: this.nomControl,
    prenom: this.prenomControl
  });

  constructor(private technicienService: TechnicienService) {
  }

  ngOnInit() {
    this.nomControl.setValue(this.technicien.nom);
    this.prenomControl.setValue(this.technicien.prenom);
  }

  updateTechnicien() {
    if (this.technicien.id != null) {
      if (this.nomControl.value != null) {
        this.technicien.nom = this.nomControl.value;
      }
      if (this.prenomControl.value != null) {
        this.technicien.prenom = this.prenomControl.value;
      }
      this.technicienService.updateTechnicien(this.technicien.id, this.technicien).subscribe(response => {
        if (response.status === 202) {
          this.cancel.emit();
        }
      });
    }
  }

  cancelUpdate() {
    this.cancel.emit();
  }

  editNom() {
    this.editingNom = true;
    this.nomControl.enable();
  }

  cancelNomUpdate() {
    this.nomControl.setValue(this.technicien.nom);
    this.editingNom = false;
    this.nomControl.disable();
  }

  editPrenom() {
    this.editingPrenom = true;
    this.prenomControl.enable();
  }

  cancelPrenomUpdate() {
    this.prenomControl.setValue(this.technicien.prenom);
    this.editingPrenom = false;
    this.prenomControl.disable();
  }
}
