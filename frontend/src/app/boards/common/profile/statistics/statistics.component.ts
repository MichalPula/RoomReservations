import {Component, OnInit} from '@angular/core';
import * as CanvasJS from 'src/app/lib/canvasjs.min.js';
import {TokenStorageService} from '../../../../services/token-storage.service';
import {CommonService} from '../../../../services/common.service';
import {ActivatedRoute, Router} from '@angular/router';

export interface ActivityAndHour {
  y: number;
  activity: string;
}

export interface Hour {
  hours: number;
}

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  adminIsSearchingStudentsStatistics: boolean;
  searchedStudentId: number;

  constructor(private tokenStorageService: TokenStorageService, private commonService: CommonService,
              private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getCurrentURL();
    this.fetchDataAndCreateCharts();
  }

  private getCurrentURL() {
    const urlWithoutParams = this.router.url.split('?')[0];
    if (urlWithoutParams === '/statistics/student') {
      this.adminIsSearchingStudentsStatistics = true;
      this.activatedRoute.queryParams.subscribe(params => {
        this.searchedStudentId = params.student;
      });
    }
  }

  private fetchDataAndCreateCharts() {
    this.commonService.getAverageHoursSpentInRoomsPerUser().subscribe(average => {
      this.commonService.getAmountOfHoursSpentByMonthByUser(
        this.adminIsSearchingStudentsStatistics ? this.searchedStudentId : this.tokenStorageService.getUser().id)
        .subscribe(hours => {this.createTimePerMonthChart(hours as Hour[], average as number);
      });
    });
    this.commonService.getAmountOfHoursSpentOnParticularActivitiesByUser(
      this.adminIsSearchingStudentsStatistics ? this.searchedStudentId : this.tokenStorageService.getUser().id)
      .subscribe(activitiesAndHours => {
      this.createTimePerActivityChart(activitiesAndHours as ActivityAndHour[]);
    });
  }

  createTimePerActivityChart(activitiesAndHours: ActivityAndHour[]) {
    const averageTimePerActivityChart = new CanvasJS.Chart('averageTimePerActivityChart', {
      theme: 'light3',
      animationEnabled: true,
      exportEnabled: false,
      title: {
        text: 'Time spent on particular activities'
      },
      data: [{
        type: 'pie',
        showInLegend: false,
        toolTipContent: '<b>{activity}</b>: {y}h (#percent%)',
        indexLabel: '{activity} - #percent%',
        dataPoints: activitiesAndHours
      }]
    });
    averageTimePerActivityChart.render();
  }

  createTimePerMonthChart(hours: Hour[], average: number) {
    const todayDate = new Date();
    const thisYear = todayDate.getFullYear();

    const monthlyHoursChart = new CanvasJS.Chart('monthlyHoursChart', {
      animationEnabled: true,
      title: {
        text: 'Time spent in rooms in particular months'
      },
      axisY: {
        title: 'Hours spent in rooms',
        suffix: 'h',
        stripLines: [{
          value: average,
          label: 'Average'
        }]
      },
      data: [{
        type: 'spline',
        dataPoints: [
          {x: new Date(thisYear, 0), y: hours[0].hours},
          {x: new Date(thisYear, 1), y: hours[1].hours},
          {x: new Date(thisYear, 2), y: hours[2].hours},
          {x: new Date(thisYear, 3), y: hours[3].hours},
          {x: new Date(thisYear, 4), y: hours[4].hours},
          {x: new Date(thisYear, 5), y: hours[5].hours},
          {x: new Date(thisYear, 6), y: hours[6].hours},
          {x: new Date(thisYear, 7), y: hours[7].hours},
          {x: new Date(thisYear, 8), y: hours[8].hours},
          {x: new Date(thisYear, 9), y: hours[9].hours},
          {x: new Date(thisYear, 10), y: hours[10].hours},
          {x: new Date(thisYear, 11), y: hours[11].hours}
        ]
      }]
    });
    monthlyHoursChart.render();
  }
}
