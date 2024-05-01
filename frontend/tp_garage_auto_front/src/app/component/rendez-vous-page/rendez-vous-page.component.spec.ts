import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezVousPageComponent } from './rendez-vous-page.component';

describe('RendezVousPageComponent', () => {
  let component: RendezVousPageComponent;
  let fixture: ComponentFixture<RendezVousPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RendezVousPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
