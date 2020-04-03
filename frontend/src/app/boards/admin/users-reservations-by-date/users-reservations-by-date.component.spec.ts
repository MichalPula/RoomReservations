import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersReservationsByDateComponent } from './users-reservations-by-date.component';

describe('UsersReservationsByDateComponent', () => {
  let component: UsersReservationsByDateComponent;
  let fixture: ComponentFixture<UsersReservationsByDateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersReservationsByDateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersReservationsByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
