import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpertregisterComponent } from './expertregister.component';

describe('ExpertregisterComponent', () => {
  let component: ExpertregisterComponent;
  let fixture: ComponentFixture<ExpertregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExpertregisterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ExpertregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
