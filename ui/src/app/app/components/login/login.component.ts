import {Component, HostListener, OnInit} from '@angular/core';
import {AuthService} from "../../classes/authentication/auth.service";
import {AuthData} from "../../classes/authentication/auth-data";
import {Router} from "@angular/router";
import {AlertService} from "../../classes/service/alert/alert.service";

@Component({
  selector: 'wn-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  password: String = null;
  login: String = null;

  @HostListener("document:keypress", ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter") {
      this.loginUser();
    }
  }

  constructor(private authService: AuthService, private router: Router, private alertService: AlertService) {
    this.authService.observableUser.subscribe(user => {
      if (user) {
        this.router.navigate(['/notes']);
      }
      else {
        this.router.navigate(['/login']);
      }
    });
  }

  ngOnInit() {
  }

  loginUser() {
    if (this.password == null || this.login == null) {
      this.alertService.addErrorMessage("Login and Password must be set up");
      return;
    }
    this.authService.login(new AuthData(this.login, this.password));
  }
}
