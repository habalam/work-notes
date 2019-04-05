import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../classes/authentication/auth.service";

@Component({
  selector: 'wn-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public currentUserName: any;

  constructor(private authService: AuthService) {
    this.authService.observableUser.subscribe((user: any) => {
      if (user) {
        this.currentUserName = user.userName;
      }
      else this.currentUserName = null;
    })
  }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
  }
}
