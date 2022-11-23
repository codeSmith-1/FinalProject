import { TestBed } from '@angular/core/testing';

import { AdultService } from './adult.service';

describe('AdultService', () => {
  let service: AdultService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdultService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
