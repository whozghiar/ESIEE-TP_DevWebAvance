import {Component, OnInit} from '@angular/core';
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {RendezVousCalendrierComponent} from "../rendez-vous-calendrier/rendez-vous-calendrier.component";
import {RendezVousModifierComponent} from "../rendez-vous-modifier/rendez-vous-modifier.component";
import {NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";

@Component({
  selector: 'app-rendez-vous-page',
  standalone: true,
  imports: [
    RendezVousCalendrierComponent,
    RendezVousModifierComponent,
    NgIf,
    LoadingSpinnerComponent
  ],
  templateUrl: './rendez-vous-page.component.html',
  styleUrl: './rendez-vous-page.component.css'
})
export class RendezVousPageComponent implements OnInit {
  selectedRendezVous: RendezVous | null = null;

  constructor() { }

  ngOnInit(): void {
  }

  onRendezVousSelected(rendezVous: RendezVous) {
    console.log('Received rendezVousSelected event', rendezVous);
    this.selectedRendezVous = rendezVous;
  }

  onRendezVousModificationCancelled() {
    this.selectedRendezVous = null;
  }
}
