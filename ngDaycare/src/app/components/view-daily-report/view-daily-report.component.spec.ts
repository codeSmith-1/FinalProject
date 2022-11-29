import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDailyReportComponent } from './view-daily-report.component';

describe('ViewDailyReportComponent', () => {
  let component: ViewDailyReportComponent;
  let fixture: ComponentFixture<ViewDailyReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDailyReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewDailyReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
