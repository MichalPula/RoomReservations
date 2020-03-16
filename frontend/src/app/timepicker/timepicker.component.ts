import { Component, OnInit } from '@angular/core';
import {NgbCalendar, NgbDate, NgbDateStruct, NgbInputDatepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'ngbd-timepicker',
  templateUrl: './timepicker.component.html',
  styleUrls: ['./timepicker.component.css'],
  providers: [NgbInputDatepickerConfig]
})
export class TimepickerComponent {

  date: NgbDateStruct;

  constructor(config: NgbInputDatepickerConfig, calendar: NgbCalendar) {
    // customize default values of datepickers used by this component tree
    config.minDate = {year: 2020, month: 3, day: 4};
    config.maxDate = {year: 2020, month: 3, day: 6};

    // days that don't belong to current month are not visible
    config.outsideDays = 'hidden';
    // weekends are disabled
    config.markDisabled = (date: NgbDate) => calendar.getWeekday(date) >= 6;

    // setting datepicker popup to close only on click outside
    config.autoClose = 'outside';
  }
}
