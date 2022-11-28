import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KidCRUDComponent } from './kid-crud.component';

describe('KidCRUDComponent', () => {
  let component: KidCRUDComponent;
  let fixture: ComponentFixture<KidCRUDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KidCRUDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KidCRUDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
