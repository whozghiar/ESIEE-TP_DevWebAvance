import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianpageComponent } from './technicianpage.component';

describe('TechnicianpageComponent', () => {
  let component: TechnicianpageComponent;
  let fixture: ComponentFixture<TechnicianpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicianpageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TechnicianpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
