import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../classes/authentication/auth.service";
import {AuthData} from "../../classes/authentication/auth-data";
import {Router} from "@angular/router";

@Component({
  selector: 'wn-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  password: String;
  login: String;

  constructor(private authService: AuthService, private router: Router) {
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
    this.authService.login(new AuthData(this.login, this.password));
  }
}
