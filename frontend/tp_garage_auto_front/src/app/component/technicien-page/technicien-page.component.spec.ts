import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienPageComponent } from './technicien-page.component';

describe('TechnicienPageComponent', () => {
  let component: TechnicienPageComponent;
  let fixture: ComponentFixture<TechnicienPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicienPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TechnicienPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
