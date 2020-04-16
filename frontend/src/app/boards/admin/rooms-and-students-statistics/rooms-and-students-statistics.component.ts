import { Component, OnInit } from '@angular/core';
import * as CanvasJS from 'src/app/lib/canvasjs.min.js';
import {TokenStorageService} from '../../../services/token-storage.service';
import {AdminService} from '../../../services/admin.service';

export interface RoomAndHours {
  roomName: string;
  amountOfHours: number;
}
export interface RoomAndHoursForChart {
  y: number;
  roomName: string;
}

export interface ActivityAndHours {
  activityName: string;
  amountOfHours: number;
}
export interface ActivityAndHoursForChart {
  y: number;
  label: string;
}

@Component({
  selector: 'app-rooms-and-students-statistics',
  templateUrl: './rooms-and-students-statistics.component.html',
  styleUrls: ['./rooms-and-students-statistics.component.css']
})
export class RoomsAndStudentsStatisticsComponent implements OnInit {

  roomsAndHours: RoomAndHours[];
  activitiesAndHours: ActivityAndHours[];

  constructor(private tokenStorageService: TokenStorageService, private adminService: AdminService) { }

  ngOnInit(): void {
    this.fetchDataAndCreateCharts();
  }

  private fetchDataAndCreateCharts() {
    this.adminService.getHoursSpentInRooms().subscribe(data => {
      const dataForChart: RoomAndHoursForChart[] = [];
      this.roomsAndHours = data as RoomAndHours[];
      this.roomsAndHours.forEach(singleValue => {
        const objectForChart: RoomAndHoursForChart = {
          y: singleValue.amountOfHours,
          roomName: singleValue.roomName
        };
        dataForChart.push(objectForChart);
      });
      this.createTimeSpentInRoomsChart(dataForChart);
    });

    this.adminService.getHoursSpentOnActivities().subscribe(data => {
      const dataForChart: ActivityAndHoursForChart[] = [];
      this.activitiesAndHours = data as ActivityAndHours[];
      this.activitiesAndHours.forEach(singleValue => {
        const objectForChart: ActivityAndHoursForChart = {
          y: singleValue.amountOfHours,
          label: singleValue.activityName
        };
        dataForChart.push(objectForChart);
      });
      this.createTimeSpentOnParticularActivityChart(dataForChart);
    });
  }

  createTimeSpentInRoomsChart(dataForChart) {
    const timeSpentInRoomsChart = new CanvasJS.Chart('timeSpentInRoomsChart', {
      theme: 'light2',
      animationEnabled: true,
      exportEnabled: false,
      title: {
        text: 'Time spent in particular rooms'
      },
      data: [{
        type: 'pie',
        showInLegend: false,
        toolTipContent: '<b>{roomName}</b>: {y}h (#percent%)',
        indexLabel: '{roomName} - #percent%',
        dataPoints: dataForChart
      }]
    });
    timeSpentInRoomsChart.render();
  }

  createTimeSpentOnParticularActivityChart(dataForChart) {
    const timeSpentOnParticularActivityChart = new CanvasJS.Chart('timeSpentOnParticularActivityChart', {
      animationEnabled: true,
      exportEnabled: false,
      title: {
        text: 'Time spent on particular activities'
      },
      data: [{
        type: 'column',
        dataPoints: dataForChart
      }]
    });
    timeSpentOnParticularActivityChart.render();
  }
}
