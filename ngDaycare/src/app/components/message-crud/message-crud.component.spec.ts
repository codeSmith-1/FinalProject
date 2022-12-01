import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageCrudComponent } from './message-crud.component';

describe('MessageCrudComponent', () => {
  let component: MessageCrudComponent;
  let fixture: ComponentFixture<MessageCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessageCrudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MessageCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
