import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAdultComponent } from './register-adult.component';

describe('RegisterAdultComponent', () => {
  let component: RegisterAdultComponent;
  let fixture: ComponentFixture<RegisterAdultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterAdultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterAdultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
