import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezVousModifierComponent } from './rendez-vous-modifier.component';

describe('RendezVousModifierComponent', () => {
  let component: RendezVousModifierComponent;
  let fixture: ComponentFixture<RendezVousModifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousModifierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RendezVousModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
