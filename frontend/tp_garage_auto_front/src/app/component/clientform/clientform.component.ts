import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-clientform',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './clientform.component.html',
  styleUrl: './clientform.component.css',
})
export class ClientformComponent {
  client = {
    id: 1,
    name: 'John',
    surname: 'Doe',
    email: 'john.doe@gmail.com',
  };

  constructor(protected clientService: ClientService) {}
}
