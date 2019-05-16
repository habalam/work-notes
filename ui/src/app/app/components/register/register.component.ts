import {Component, OnInit} from '@angular/core';
import {AlertService} from "../../classes/service/alert/alert.service";
import {UserAdministrationService} from "../../classes/service/user/useradministration/user-administration.service";
import {UserData} from "../../classes/service/user/user-data";

@Component({
  selector: 'wn-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  //TODO nahradiť classov
  userData: UserData = new UserData();
  passwordCheck: string;

  constructor(private alertService: AlertService, private userAdministrationService: UserAdministrationService) {
  }

  ngOnInit() {
  }

  registerUser() {
    if (this.userData.userName != null && this.userData.password != null) {
      if (this.userData.password !== this.passwordCheck) {
        this.alertService.addErrorMessage("Password not equals as password check!");
      }
      else {
        this.userAdministrationService.registerUser(this.userData).subscribe( () => {
          this.alertService.addInfoMessage("User registered");
        }, () => {
          this.alertService.addErrorMessage("User was not registered. Please contact administrator.")
        });
      }
    }
    else {
      this.alertService.addErrorMessage("Login and Password must be set up!");
    }
  }
}
