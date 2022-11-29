import { TestBed } from '@angular/core/testing';

import { BathroomService } from './bathroom.service';

describe('BathroomService', () => {
  let service: BathroomService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BathroomService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
