import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TasksByDayComponent} from './tasks-by-day.component';

describe('TasksByDayComponent', () => {
  let component: TasksByDayComponent;
  let fixture: ComponentFixture<TasksByDayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksByDayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksByDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
