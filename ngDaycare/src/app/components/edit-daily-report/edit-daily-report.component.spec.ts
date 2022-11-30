import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDailyReportComponent } from './edit-daily-report.component';

describe('EditDailyReportComponent', () => {
  let component: EditDailyReportComponent;
  let fixture: ComponentFixture<EditDailyReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditDailyReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditDailyReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
