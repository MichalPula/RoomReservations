import {Directive, OnInit} from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';
import {CommonService} from '../../../services/common.service';
import {AdminService} from '../../../services/admin.service';

@Directive({
  selector: '[appRoomNameValidateDirective]',
  providers: [
    CommonService,
    {
    provide: NG_VALIDATORS,
    useExisting: RoomNameValidateDirective,
    multi: true
  }]
})

export class RoomNameValidateDirective implements Validator, OnInit {

  constructor(private adminService: AdminService) {}

  roomsNames: string[] = [];

  ngOnInit(): void {
    this.roomsNames = this.adminService.getRoomsNames();
  }

  validate(control: AbstractControl): {[key: string]: any} | null {
    const pickedRoomName: string = control.value;
    if (this.roomsNames.includes(pickedRoomName, 0)) {
      return { roomNameInvalid: true };
    }
    return null;
  }
}
