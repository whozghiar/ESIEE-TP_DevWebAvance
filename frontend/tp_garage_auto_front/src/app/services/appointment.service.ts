import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  private appointmentArray: any[] = [];
  constructor(private httpClient: HttpClient) {
    this.refreshAppointments();
  }

  refreshAppointments() {
    this.httpClient.get('/api/rendez-vous').subscribe((appointments: any) => {
      this.appointmentArray = appointments;
    });
  }

  getAllAppointments() {
    return this.appointmentArray;
  }

  addAppointment(appointmentSent: any) {
    let appointment = {
      client: appointmentSent.client,
      vehicle: appointmentSent.vehicle,
      date: appointmentSent.date,
      status: appointmentSent.status,
    };
    this.httpClient.post('/api/rendez-vous', appointment).subscribe(() => {
      this.refreshAppointments();
    });
  }

  removeAppointment(id: number) {
    this.httpClient.delete('/api/rendez-vous/' + id).subscribe(() => {
      this.refreshAppointments();
    });
  }
}
