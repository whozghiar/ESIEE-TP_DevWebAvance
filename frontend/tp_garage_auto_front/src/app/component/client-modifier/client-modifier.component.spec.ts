import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientModifierComponent } from './client-modifier.component';

describe('ClientModifierComponent', () => {
  let component: ClientModifierComponent;
  let fixture: ComponentFixture<ClientModifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientModifierComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
