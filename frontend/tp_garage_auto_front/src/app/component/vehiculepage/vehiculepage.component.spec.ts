import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculepageComponent } from './vehiculepage.component';

describe('VehiculepageComponent', () => {
  let component: VehiculepageComponent;
  let fixture: ComponentFixture<VehiculepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehiculepageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehiculepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
