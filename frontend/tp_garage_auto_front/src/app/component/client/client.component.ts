import { Component, Input } from '@angular/core';
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
  NgOnInit() {
    console.log('Client initialization...');
  }

  @Input('client')
  client: any = {
    name: 'UNKNOWN',
    surname: 'UNKNOWN',
    phone: 'UNKNOWN',
    email: 'UNKNOWN',
    vehicules: [],
  };
}
