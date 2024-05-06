import {Component, OnInit} from '@angular/core';
import {Technicien} from "../../modeles/TechnicienModele/technicien";
import {TechnicienService} from "../../services/TechnicienService/technicien.service";
import {TechnicienCardComponent} from "../technicien-card/technicien-card.component";
import {NgForOf, NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";

@Component({
  selector: 'app-technicien-page',
  standalone: true,
    imports: [
        TechnicienCardComponent,
        NgForOf,
        LoadingSpinnerComponent,
        NgIf
    ],
  templateUrl: './technicien-page.component.html',
  styleUrl: './technicien-page.component.css'
})

export class TechnicienPageComponent implements OnInit {
  techniciens!: Technicien[];
  isLoading: boolean = true;

  constructor(private technicienService: TechnicienService) { }

  ngOnInit(): void {
    this.loadTechniciens();
  }

  onTechnicienDeleted(index: number): void {
    this.techniciens.splice(index, 1);
  }

  loadTechniciens(): void {
    this.technicienService.getTechniciens().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.techniciens = response.body;
      }
      this.isLoading = false;
    });
  }
}
