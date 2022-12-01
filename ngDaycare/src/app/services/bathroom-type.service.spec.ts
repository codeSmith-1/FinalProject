import { TestBed } from '@angular/core/testing';

import { BathroomTypeService } from './bathroom-type.service';

describe('BathroomTypeService', () => {
  let service: BathroomTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BathroomTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
