import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezVousCalendrierComponent } from './rendez-vous-calendrier.component';

describe('RendezVousCalendrierComponent', () => {
  let component: RendezVousCalendrierComponent;
  let fixture: ComponentFixture<RendezVousCalendrierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousCalendrierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RendezVousCalendrierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
