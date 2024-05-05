import {Component, Input} from '@angular/core';
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-rendez-vous-card',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './rendez-vous-card.component.html',
  styleUrl: './rendez-vous-card.component.css'
})
export class RendezVousCardComponent {
  @Input() rendezVous!: RendezVous;

  constructor(
    protected authguardService: AuthguardService,
  ) {}

  isUserAuthorized(): boolean {
    const res = false;
    if (this.authguardService.isAdmin() || this.authguardService.isTechnicien()) {
      return true;
    }
    return res;
  }
}
