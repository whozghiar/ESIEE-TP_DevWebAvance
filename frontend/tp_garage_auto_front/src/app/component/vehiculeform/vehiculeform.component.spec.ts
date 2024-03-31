import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculeformComponent } from './vehiculeform.component';

describe('VehiculeformComponent', () => {
  let component: VehiculeformComponent;
  let fixture: ComponentFixture<VehiculeformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiculeformComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehiculeformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
