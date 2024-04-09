import { Component } from '@angular/core';
import { NgFor, NgForOf } from '@angular/common';
import { TechnicianComponent } from '../technician/technician.component';
import { TechnicianformComponent } from '../technicianform/technicianform.component';
import { AppointmentformComponent } from '../appointmentform/appointmentform.component';
import { VehiculeComponent } from '../vehicule/vehicule.component';
import { ClientformComponent } from '../clientform/clientform.component';

@Component({
  selector: 'app-clientpage',
  standalone: true,
  imports: [
    NgForOf,
    VehiculeComponent,
    ClientformComponent,
    AppointmentformComponent,
  ],
  templateUrl: './clientpage.component.html',
  styleUrl: './clientpage.component.css',
})
export class ClientpageComponent {
  clients: any = [
    {
      name: 'John',
      surname: 'Doe',
      phone: '123456789',
      email: 'john@mail.com',
      vehicules: [
        {
          brand: 'Toyota',
          model: 'Corolla',
          year: 2010,
          plate: '123ABC',
        },
        {
          brand: 'Ford',
          model: 'Fiesta',
          year: 2015,
          plate: '456DEF',
        },
      ],
    },
  ];

  constructor() {}
}
