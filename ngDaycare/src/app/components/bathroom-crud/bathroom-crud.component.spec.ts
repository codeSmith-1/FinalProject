import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BathroomCrudComponent } from './bathroom-crud.component';

describe('BathroomCrudComponent', () => {
  let component: BathroomCrudComponent;
  let fixture: ComponentFixture<BathroomCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BathroomCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BathroomCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
