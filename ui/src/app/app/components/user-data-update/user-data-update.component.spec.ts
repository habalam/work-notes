import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UserDataUpdateComponent} from './user-data-update.component';

describe('UserDataUpdateComponent', () => {
  let component: UserDataUpdateComponent;
  let fixture: ComponentFixture<UserDataUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDataUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDataUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
