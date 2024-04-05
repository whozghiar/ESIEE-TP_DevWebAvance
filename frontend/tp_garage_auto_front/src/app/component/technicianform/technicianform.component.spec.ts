import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianformComponent } from './technicianform.component';

describe('TechnicianformComponent', () => {
  let component: TechnicianformComponent;
  let fixture: ComponentFixture<TechnicianformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicianformComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TechnicianformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
