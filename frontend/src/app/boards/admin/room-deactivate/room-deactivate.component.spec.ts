import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomDeactivateComponent } from './room-deactivate.component';

describe('RoomDeactivateComponent', () => {
  let component: RoomDeactivateComponent;
  let fixture: ComponentFixture<RoomDeactivateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomDeactivateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomDeactivateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
