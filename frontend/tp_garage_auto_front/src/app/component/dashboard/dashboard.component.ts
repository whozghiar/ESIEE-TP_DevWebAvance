import { Component } from '@angular/core';
import { AppointmentformComponent } from '../appointmentform/appointmentform.component';
import { NgForOf } from '@angular/common';
import { VehiculeformComponent } from '../vehiculeform/vehiculeform.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [AppointmentformComponent, NgForOf, VehiculeformComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {}
