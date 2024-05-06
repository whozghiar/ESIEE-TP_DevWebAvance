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

  nomControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  prenomControl = new FormControl({value:'',disabled:true}, [Validators.required]);
  emailControl = new FormControl({value:'',disabled:true}, [Validators.required, Validators.email]);

  editingNom = false;
  editingPrenom = false;
  editingEmail = false;

  formGroup = new FormGroup({
    nom: this.nomControl,
    prenom: this.prenomControl,
    email: this.emailControl
  });

  constructor(
    private technicienService: TechnicienService
  ) {  }

  ngOnInit() {
    this.nomControl.setValue(this.technicien.nom);
    this.prenomControl.setValue(this.technicien.prenom);
    this.emailControl.setValue(this.technicien.email);
  }

  updateTechnicien() {
    this.updateTechnicienWithForm();
    this.saveTechnicien();
  }

  /**
   * Met à jour le technicien avec les valeurs du formulaire
   */
  updateTechnicienWithForm() {
    if (this.nomControl.value != null) {
      this.technicien.nom = this.nomControl.value;
    }
    if (this.prenomControl.value != null) {
      this.technicien.prenom = this.prenomControl.value;
    }
  }

  /**
   * Sauvegarde les modifications du technicien
   */
  saveTechnicien() {
    console.log('Technicien à mettre à jour : ', this.technicien)
    if(this.technicien.id != null) {
      this.technicienService.updateTechnicien(this.technicien.id, this.technicien).subscribe(response => {
        if (response.status === 202) {
          console.log('Technicien mis à jour');
          this.cancelUpdate();
        }
      });
    }
  }

  /**
   * Annule la modification du technicien
   */
  cancelUpdate() {
    this.cancel.emit();
  }

  /**
   * Active le mode édition pour le champ nom
   */
  editNom() {
    this.editingNom = true;
    this.nomControl.enable();
  }

  /**
   * Annule la modification du champ nom et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelNomUpdate() {
    this.nomControl.setValue(this.technicien.nom);
    this.editingNom = false;
    this.nomControl.disable();
  }

  /**
   * Active le mode édition pour le champ prénom
   */
  editPrenom() {
    this.editingPrenom = true;
    this.prenomControl.enable();
  }

  /**
   * Annule la modification du champ prénom et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelPrenomUpdate() {
    this.prenomControl.setValue(this.technicien.prenom);
    this.editingPrenom = false;
    this.prenomControl.disable();
  }

  /**
   * Active le mode édition pour le champ email
   */
  editEmail() {
    this.editingEmail = true;
    this.emailControl.enable();
  }

  /**
   * Annule la modification du champ email et réinitialise la valeur
   * Désactive le mode édition
   */
  cancelEmailUpdate() {
    this.emailControl.setValue(this.technicien.email);
    this.editingEmail = false;
    this.emailControl.disable();
  }
}
