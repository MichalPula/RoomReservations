import { Component, OnInit } from '@angular/core';
import * as CanvasJS from 'src/app/lib/canvasjs.min.js';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.createChart();


  }

  private createChart() {
    const chart = new CanvasJS.Chart('chartContainer', {
      theme: 'light3',
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: 'Time spent on particular activities'
      },
      data: [{
        type: 'pie',
        showInLegend: false,
        toolTipContent: '<b>{name}</b>: ${y} (#percent%)',
        indexLabel: '{name} - #percent%',
        dataPoints: [
          { y: 450, name: 'Food' },
          { y: 120, name: 'Insurance' },
          { y: 300, name: 'Traveling' },
          { y: 2000, name: 'Housing' },
          { y: 150, name: 'Education' },
          { y: 150, name: 'Shopping'},
          { y: 250, name: 'Others' }
        ]
      }]
    });

    chart.render();
  }
}
