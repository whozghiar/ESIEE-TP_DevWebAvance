import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientService } from '../../services/client.service';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-clientform',
  standalone: true,
  imports: [NgForOf, ReactiveFormsModule, FormsModule],
  templateUrl: './clientform.component.html',
  styleUrl: './clientform.component.css',
})
export class ClientformComponent {
  client = {
    name: '',
    surname: '',
    phone: '',
    email: '',
    vehicules: [],
  };

  constructor(protected clientService: ClientService) {}
}
