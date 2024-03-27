import { Component } from '@angular/core';

@Component({
  selector: 'app-technicianpage',
  standalone: true,
  imports: [],
  templateUrl: './technicianpage.component.html',
  styleUrl: './technicianpage.component.css'
})
export class TechnicianpageComponent {
  technicians : any  = [
    { id: 1, name: 'John' ,surname: 'Padawan'},
    { id: 2, name: 'Lucien', surname: 'Bobby'},
    { id: 3, name: 'Theo', surname: 'Lomege'},
    // Ajoutez plus de techniciens selon les besoins...
  ];
  constructor() { }
}
