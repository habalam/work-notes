import {TestBed} from '@angular/core/testing';

import {UserAdministrationService} from './user-administration.service';

describe('UserAdministrationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserAdministrationService = TestBed.get(UserAdministrationService);
    expect(service).toBeTruthy();
  });
});
