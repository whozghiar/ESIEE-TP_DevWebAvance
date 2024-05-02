import {Component, OnInit} from '@angular/core';
import {RendezVousService} from "../../services/RendezVousService/rendez-vous.service";
import {NgForOf, NgIf} from "@angular/common";
import {LoadingSpinnerComponent} from "../loading-spinner/loading-spinner.component";
import {FormsModule} from "@angular/forms";

interface Day {
  date: number;
  hasAppointment: boolean;
  disabled: boolean;
}

@Component({
  selector: 'app-rendez-vous-calendrier',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    LoadingSpinnerComponent,
    FormsModule
  ],
  templateUrl: './rendez-vous-calendrier.component.html',
  styleUrl: './rendez-vous-calendrier.component.css'
})
export class RendezVousCalendrierComponent implements OnInit {

  currentMonth: number;
  currentYear: number;
  days: Day[] = [];
  weekdays = ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'];
  months = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
  years = Array(80).fill(0).map((x,i)=>i+2000); // Années de 2000 à 2079
  isLoading = true;

  constructor(private rendezVousService: RendezVousService) {
    const date = new Date();
    this.currentMonth = date.getMonth();
    this.currentYear = date.getFullYear();
  }


  ngOnInit(): void {
    this.getDaysInMonth();
  }

  getDaysInMonth(): void {
    this.isLoading = true;
    const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();

    this.days = [];
    for (let i = 1; i <= daysInMonth; i++) {
      this.days.push({ date: i, hasAppointment: false, disabled: false });
    }


    this.rendezVousService.getRendezVous().subscribe(response => {
      if (response.body !== null) {
        response.body.forEach(rendezVous => {
          const rendezVousDate = this.parseDate(rendezVous.date);
          if (rendezVousDate.getMonth() === +this.currentMonth && rendezVousDate.getFullYear() === +this.currentYear) {
            let day = this.days.find(day => day.date === rendezVousDate.getDate());
            if (day) {
              day.hasAppointment = true;
              day.disabled = true;
            }
          }
        });
      }
      this.isLoading = false;
    });
  }


  prevMonth(): void {
    if (this.currentMonth > 0) {
      this.currentMonth--;
    } else {
      this.currentMonth = 11;
      this.currentYear--;
    }
    this.getDaysInMonth();
  }

  nextMonth(): void {
    if (this.currentMonth < 11) {
      this.currentMonth++;
    } else {
      this.currentMonth = 0;
      this.currentYear++;
    }
    this.getDaysInMonth();
  }

  parseDate(dateString: string): Date {
    const [day, month, year] = dateString.split("/");
    return new Date(parseInt(year), parseInt(month) - 1, parseInt(day));
  }
}
