import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezVousCardComponent } from './rendez-vous-card.component';

describe('RendezVousCardComponent', () => {
  let component: RendezVousCardComponent;
  let fixture: ComponentFixture<RendezVousCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RendezVousCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
