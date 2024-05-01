import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienModifierComponent } from './technicien-modifier.component';

describe('TechnicienModifierComponent', () => {
  let component: TechnicienModifierComponent;
  let fixture: ComponentFixture<TechnicienModifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicienModifierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TechnicienModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
