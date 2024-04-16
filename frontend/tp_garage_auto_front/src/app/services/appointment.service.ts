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
    console.log(this.appointmentArray, 'appointments refresh');
  }

  getAllAppointments() {
    this.refreshAppointments();
    return this.appointmentArray;
  }

  addAppointment(appointmentSent: any) {
    let appointment = {
      //technicien: appointmentSent.technicien,
      //vehicule: appointmentSent.vehicule,
      date: appointmentSent.date,
      typeService: appointmentSent.typeService,
    };
    this.httpClient.post('/api/rendez-vous', appointment).subscribe(() => {
      this.refreshAppointments();
    });
    console.log(appointment, 'appointment added');
  }

  removeAppointment(id: number) {
    this.httpClient.delete('/api/rendez-vous/' + id).subscribe(() => {
      this.refreshAppointments();
    });
  }
}
