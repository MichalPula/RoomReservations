import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomsAndStudentsStatisticsComponent } from './rooms-and-students-statistics.component';

describe('RoomsAndStudentsStatisticsComponent', () => {
  let component: RoomsAndStudentsStatisticsComponent;
  let fixture: ComponentFixture<RoomsAndStudentsStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomsAndStudentsStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomsAndStudentsStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
