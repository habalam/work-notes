import {Component, OnDestroy, OnInit} from '@angular/core';
import {AlertService} from "../../classes/service/alert/alert.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'wn-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit, OnDestroy {

  private subsription: Subscription;
  message: string;

  constructor(private alertService: AlertService) { }

  ngOnInit() {
    this.subsription = this.alertService.observableMessage.subscribe((alertMessage: string) => {
      this.message = alertMessage;
    });
  }

  ngOnDestroy() {
    this.subsription.unsubscribe();
  }
}
