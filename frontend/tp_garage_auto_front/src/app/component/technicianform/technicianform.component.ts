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
    name: '',
    surname: '',
    availability: true,
    avatar: '',
    description: '',
  };
  constructor(protected technicianService: TechnicianService) {}
}
