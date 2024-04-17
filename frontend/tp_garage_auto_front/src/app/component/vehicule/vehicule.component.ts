import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TechnicianComponent } from '../technician/technician.component';
import { NgClass, NgSwitch, NgSwitchCase } from '@angular/common';

@Component({
  selector: 'app-vehicule',
  standalone: true,
  imports: [NgClass, NgSwitch, NgSwitchCase],
  templateUrl: './vehicule.component.html',
  styleUrl: './vehicule.component.css',
})
export class VehiculeComponent {
  @Input() column!: number;
  @Input('vehicle')
  vehicle: any = {};

  @Output('trigger')
  trigger: EventEmitter<any> = new EventEmitter<any>();

  launchTrigger() {
    this.trigger.emit();
  }
}
