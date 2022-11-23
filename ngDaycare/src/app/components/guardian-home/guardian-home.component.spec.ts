import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardianHomeComponent } from './guardian-home.component';

describe('GuardianHomeComponent', () => {
  let component: GuardianHomeComponent;
  let fixture: ComponentFixture<GuardianHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuardianHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardianHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
