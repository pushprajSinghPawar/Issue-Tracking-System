import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpertdashboardComponent } from './expertdashboard.component';

describe('ExpertdashboardComponent', () => {
  let component: ExpertdashboardComponent;
  let fixture: ComponentFixture<ExpertdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExpertdashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ExpertdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
