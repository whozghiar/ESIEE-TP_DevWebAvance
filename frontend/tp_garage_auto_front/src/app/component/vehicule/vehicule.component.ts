import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TechnicianComponent}  from "../technician/technician.component";

@Component({
  selector: 'app-vehicule',
  standalone: true,
  imports: [],
  templateUrl: './vehicule.component.html',
  styleUrl: './vehicule.component.css'
})
export class VehiculeComponent {

  @Input("vehicle")
  vehicle: any = {
    technician: TechnicianComponent,
    client: "UNKNOWN",
    issue: "UNKNOWN",
    model: "UNKNOWN",
  }

  @Output("trigger")
  trigger: EventEmitter<any> = new EventEmitter<any>();


  launchTrigger() {
    this.trigger.emit();
  }
}
