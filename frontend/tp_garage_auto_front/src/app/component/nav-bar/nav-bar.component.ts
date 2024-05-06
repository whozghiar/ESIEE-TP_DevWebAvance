import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgIf } from '@angular/common';
import {AuthguardService} from "../../services/AuthGuardService/authguard.service";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, NgIf],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent {
  isDropdownOpen = false; // Ajoute cette ligne

  constructor(
    protected authGuardService: AuthguardService
  ) {
  }
}
