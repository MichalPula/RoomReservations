import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentByNameComponent } from './student-by-name.component';

describe('StudentByNameComponent', () => {
  let component: StudentByNameComponent;
  let fixture: ComponentFixture<StudentByNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentByNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentByNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
