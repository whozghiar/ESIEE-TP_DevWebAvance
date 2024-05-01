import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculeCardComponent } from './vehicule-card.component';

describe('VehiculeCardComponent', () => {
  let component: VehiculeCardComponent;
  let fixture: ComponentFixture<VehiculeCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiculeCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehiculeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
