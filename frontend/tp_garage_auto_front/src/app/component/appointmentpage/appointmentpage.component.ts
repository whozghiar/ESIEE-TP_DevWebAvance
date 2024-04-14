import { Component, OnInit } from '@angular/core';
import { NgForOf } from '@angular/common';
import { AppointmentService } from '../../services/appointment.service';
import { AppointmentformComponent } from '../appointmentform/appointmentform.component';

interface Appointment {
  id: number;
  typeService: string;
  date: string;
  vehicule: {};
  technicien: {};
}

interface Day {
  date: number; // Vous pouvez aussi utiliser un type Date selon vos besoins
  appointments: Appointment[];
}

@Component({
  selector: 'app-appointmentpage',
  standalone: true,
  imports: [NgForOf, AppointmentformComponent],
  templateUrl: './appointmentpage.component.html',
  styleUrl: './appointmentpage.component.css',
})
export class AppointmentpageComponent implements OnInit {
  appointment: any = {
    date: '',
    typeService: '',
    vehicule: '',
    technicien: '',
  };

  // Exemple de données pour le calendrier
  /*days: Day[] = [
    {
      date: '1',
      appointments: [
        { time: '10:00', clientName: 'Jean Dupont' },
        { time: '14:00', clientName: 'Marie Curie' },
      ],
    },
    {
      date: '2',
      appointments: [{ time: '11:00', clientName: 'Albert Einstein' }],
    },
    // Continuez pour les autres jours...
  ];*/

  currentMonth: number = new Date().getMonth() + 1; // JavaScript's months are 0-based
  currentYear: number = new Date().getFullYear();
  days: Day[] = [];

  constructor(protected appointmentService: AppointmentService) {
    this.days = this.appointmentService.getAllAppointments();
    console.log(this.days, 'days appointements');
  }

  ngOnInit() {
    this.generateDays(this.currentMonth, this.currentYear);
  }

  generateDays(month: number, year: number) {
    let daysInMonth = new Date(year, month, 0).getDate();
    let daysArray = [];
    let appointments = this.appointmentService.getAllAppointments();

    for (let i = 1; i <= daysInMonth; i++) {
      let dayAppointments = appointments.filter((appointment) => {
        let [day, month, year] = appointment.date.split('/').map(Number);
        return (
          day === i && month === this.currentMonth && year === this.currentYear
        );
      });
      daysArray.push({
        date: i,
        appointments: dayAppointments,
      });
    }

    this.days = daysArray;
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
