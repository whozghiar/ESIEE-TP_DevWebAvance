import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Input('client')
  client: any = {};

  @Output('delete')
  delete = new EventEmitter<string>();

  deleteClient() {
    this.delete.emit();
  }

  logClient() {
    console.log(this.client);
  }
}
