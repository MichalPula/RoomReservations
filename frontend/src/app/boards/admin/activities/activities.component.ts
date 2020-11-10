import { Component, OnInit } from '@angular/core';
import {Activity, CommonService} from '../../../services/common.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminService} from '../../../services/admin.service';
import {ActivatedRoute, Router} from '@angular/router';

export interface ActivityAddUpdateForm {
  name: string;
  authorities: string[];
  available: boolean;
}

export interface Role {
  formattedRoleType: string;
}

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

  public activities: Activity[];
  public roles: Role[];
  idOfActivityToUpdate: number;

  config: any;

  activityAddForm: ActivityAddUpdateForm = {
    name: '',
    authorities: [] = [],
    available: null
  };

  activityUpdateForm: ActivityAddUpdateForm = {
    name: '',
    authorities: [] = [],
    available: null
  };

  constructor(private commonService: CommonService, private modalService: NgbModal,
              private adminService: AdminService,  private router: Router,  private route: ActivatedRoute) {
    this.config =  {
      currentPage: 1,
      itemsPerPage: 8,
      totalItems: 0
    };
    route.queryParams.subscribe(
      params => this.config.currentPage = params.page ? params.page : 1);
  }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData() {
    this.commonService.getAllActivities().subscribe(data => {
      this.activities = data as Activity[];
    });
    this.adminService.getAllRoles().subscribe(data => {
      this.roles = data as Role[];
    });
  }

  openAddActivityModal(content) {
    this.activityAddForm.name  = '';
    this.activityAddForm.authorities  = [];
    this.activityAddForm.available  = null;
    this.modalService.open(content, { centered: true });
  }

  addActivity() {
    console.log(this.activityAddForm);
    this.adminService.addActivity(this.activityAddForm).subscribe(data => {
      this.refreshPage();
    });
  }

  openUpdateActivityModal(content, activityId, activityName, authorities) {
    this.idOfActivityToUpdate = activityId;
    this.activityUpdateForm.name = activityName;
    this.activityUpdateForm.authorities = authorities;
    this.modalService.open(content, { centered: true });
  }

  updateActvitity() {
    this.adminService.updateActivity(this.idOfActivityToUpdate, this.activityUpdateForm).subscribe(data => {
      this.refreshPage();
    });
  }

  deactivateAcivity(activityId: number) {
    this.adminService.deactivateActivity(activityId).subscribe(data => {
      this.fetchData();
    });
  }

  refreshPage() {
    window.location.replace('/activities');
  }

  changePage(newPage: number) {
    this.router.navigate(['/activities/'], {queryParams: {page: newPage}});
  }
}
