import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienCardComponent } from './technicien-card.component';

describe('TechnicienCardComponent', () => {
  let component: TechnicienCardComponent;
  let fixture: ComponentFixture<TechnicienCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicienCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TechnicienCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
