import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-technician',
  standalone: true,
  imports: [],
  templateUrl: './technician.component.html',
  styleUrl: './technician.component.css',
})
export class TechnicianComponent {
  NgOnInit() {
    console.log('TechnicianComponent initialized');
  }

  @Input('technician')
  technician: any = {
    name: 'UNKNOWN',
    surname: 'UNKNOWN',
    availability: true,
    avatar: '',
    description: 'UNKNOWN',
  };
}
