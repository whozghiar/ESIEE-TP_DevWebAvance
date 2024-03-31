import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TechnicianComponent}  from "../technician/technician.component";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-vehicule',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './vehicule.component.html',
  styleUrl: './vehicule.component.css'
})
export class VehiculeComponent {

  @Input("vehicle")
  vehicle: any = {
    id: 0,
    technician: "UNKNOWN",//TechnicianComponent,
    client: "UNKNOWN",
    issue: "UNKNOWN",
    model: "UNKNOWN",
    status: false,
  }

  @Output("trigger")
  trigger: EventEmitter<any> = new EventEmitter<any>();


  launchTrigger() {
    this.trigger.emit();
  }
}
