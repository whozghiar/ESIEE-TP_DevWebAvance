import { Component } from '@angular/core';
import { NgForOf } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehicleService } from '../../services/vehicle.service';
import { TechnicianService } from '../../services/technician.service';

@Component({
  selector: 'app-technicianform',
  standalone: true,
  imports: [NgForOf, ReactiveFormsModule, FormsModule],
  templateUrl: './technicianform.component.html',
  styleUrl: './technicianform.component.css',
})
export class TechnicianformComponent {
  technician: any = {
    nom: '',
    prenom: '',
    availability: true,
    avatar: 'https://www.w3schools.com/howto/img_avatar.png',
  };
  logTechnician() {
    console.log(this.technician);
  }
  constructor(protected technicianService: TechnicianService) {}
}
