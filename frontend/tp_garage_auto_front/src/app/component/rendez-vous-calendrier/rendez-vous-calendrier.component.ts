import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {RendezVous} from "../../modeles/RendezVousModele/rendez-vous";
import {RendezVousFormComponent} from "../rendez-vous-form/rendez-vous-form.component";
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";


interface Day {
  date: string;
  rendezVous: RendezVous | null;
}

@Component({
  selector: 'app-rendez-vous-calendrier',
  standalone: true,
  imports: [NgForOf, RendezVousFormComponent, NgIf, LoadingSpinnerComponent, NgClass],
  templateUrl: './rendez-vous-calendrier.component.html',
  styleUrl: './rendez-vous-calendrier.component.html'
})
export class RendezVousCalendrierComponent implements OnInit {

  rdv!: RendezVous;
  rendezVous: RendezVous[] = [];

  @ViewChild(RendezVousFormComponent) formComponent!: RendezVousFormComponent;

  currentMonth: number = new Date().getMonth() + 1;
  currentYear: number = new Date().getFullYear();
  days: Day[] = [];

  selectedDate: string = '';

  loadingRendezVous : boolean = true;

  constructor(
    protected rendezVousService: RendezVousService
  ) {}

  ngOnInit() {
    this.loadRendezVous();
  }

  /**
   * Appelle le service pour récupérer la liste des rendez-vous
   */
  loadRendezVous(): void {
    this.selectedDate = '';
    this.rendezVousService.getRendezVous().subscribe(response => {
      if (response.body !== null && response.status === 200) {
        this.rendezVous = response.body;
        this.loadingRendezVous = false;
        this.generateDays(this.currentMonth, this.currentYear);
      }
    });
  }

  generateDays(month: number, year: number) {
    let daysInMonth = new Date(year, month, 0).getDate();
    this.days = [];

    for (let i = 1; i <= daysInMonth; i++) {

      const dayString = String(i).padStart(2, '0');
      const monthString = String(month).padStart(2, '0');
      const yearString = String(year);

      const dateString = `${dayString}/${monthString}/${yearString}`;

      let jourRendezVous = this.rendezVous.find(rendezVous => rendezVous.date === dateString);

        this.days.push({
        date: dateString,
        rendezVous: jourRendezVous || null
      });
    }
  }

  hasRendezVous(date: string): boolean {
    return this.days.some(day => day.date === date && day.rendezVous !== null);
  }

  handleDayClick(day: string) {
    if (!this.hasRendezVous(day)) {
      this.selectedDate = day;
      if (this.formComponent) {
        this.formComponent.setDate(this.selectedDate);
      }
    }

  }

  prevMonth() {
    if (this.currentMonth > 1) {
      this.currentMonth--;
    } else {
      this.currentMonth = 12;
      this.currentYear--;
    }
    this.generateDays(this.currentMonth, this.currentYear);
  }

  nextMonth() {
    if (this.currentMonth < 12) {
      this.currentMonth++;
    } else {
      this.currentMonth = 1;
      this.currentYear++;
    }
    this.generateDays(this.currentMonth, this.currentYear);
  }

}
