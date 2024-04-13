import { Component } from '@angular/core';
import { ClientformComponent } from '../clientform/clientform.component';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [ClientformComponent, NgForOf],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
})
export class ClientComponent {
  clients = [
    {
      id: 1,
      name: 'John',
      surname: 'Doe',
      photo: '1234567890',
      email: 'joh@sf.fr',
    },
    {
      id: 2,
      name: 'Jane',
      surname: 'Doe',
      email: 'esfsef@zer.fr',
    },
  ];
}
