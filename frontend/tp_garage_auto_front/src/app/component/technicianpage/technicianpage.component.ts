import { Component } from '@angular/core';
import { NgForOf } from '@angular/common';
import { TechnicianComponent } from '../technician/technician.component';
import { TechnicianformComponent } from '../technicianform/technicianform.component';
import { AppointmentformComponent } from '../appointmentform/appointmentform.component';

@Component({
  selector: 'app-technicianpage',
  standalone: true,
  imports: [
    NgForOf,
    TechnicianComponent,
    TechnicianformComponent,
    AppointmentformComponent,
  ],
  templateUrl: './technicianpage.component.html',
  styleUrl: './technicianpage.component.css',
})
export class TechnicianpageComponent {
  technicians: any = [
    {
      name: 'John',
      surname: 'Doe',
      availability: true,
      avatar: 'https://www.w3schools.com/howto/img_avatar.png',
    },
    {
      name: 'Jane',
      surname: 'Doe',
      availability: false,
      avatar: 'https://www.w3schools.com/howto/img_avatar2.png',
    },
    {
      name: 'Jack',
      surname: 'Doe',
      availability: true,
      avatar: 'https://www.w3schools.com/howto/img_snow.jpg',
    },
  ];
  constructor() {}
}
