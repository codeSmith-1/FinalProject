import { TestBed } from '@angular/core/testing';

import { NapService } from './nap.service';

describe('NapService', () => {
  let service: NapService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NapService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
