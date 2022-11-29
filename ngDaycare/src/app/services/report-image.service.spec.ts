import { TestBed } from '@angular/core/testing';

import { ReportImageService } from './report-image.service';

describe('ReportImageService', () => {
  let service: ReportImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
