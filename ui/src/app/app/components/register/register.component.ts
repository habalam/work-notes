import {Component, OnInit} from '@angular/core';
import {AlertService} from "../../classes/service/alert/alert.service";

@Component({
  selector: 'wn-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  login: string;
  password: string;
  passwordCheck: string;
  email: string;

  constructor(private alertService: AlertService) {
  }

  ngOnInit() {
  }

  registerUser() {
    if (this.login != null && this.password != null) {
      if (this.password !== this.passwordCheck) {
        this.alertService.addInfoMessage("Password not equals as password check!");
      }
      else {
        this.alertService.addInfoMessage("User registered");
      }
    }
    else {
      this.alertService.addInfoMessage("Login and Password must be set up!");
    }
  }
}
