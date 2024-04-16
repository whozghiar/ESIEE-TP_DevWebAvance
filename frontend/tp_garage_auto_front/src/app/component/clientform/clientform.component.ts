import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Input('client')
  client = {
    nom: '',
    prenom: '',
    telephone: '',
    email: '',
  };

  @Output('add')
  add = new EventEmitter<string>();

  addClient(client: any) {
    this.add.emit(client);
  }

  logClient() {
    console.log(this.client);
  }
}
