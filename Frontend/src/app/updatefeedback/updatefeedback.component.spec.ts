import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatefeedbackComponent } from './updatefeedback.component';

describe('UpdatefeedbackComponent', () => {
  let component: UpdatefeedbackComponent;
  let fixture: ComponentFixture<UpdatefeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdatefeedbackComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdatefeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
