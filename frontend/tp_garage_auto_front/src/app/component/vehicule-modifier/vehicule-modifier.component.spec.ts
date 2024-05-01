import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculeModifierComponent } from './vehicule-modifier.component';

describe('VehiculeModifierComponent', () => {
  let component: VehiculeModifierComponent;
  let fixture: ComponentFixture<VehiculeModifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiculeModifierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehiculeModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
