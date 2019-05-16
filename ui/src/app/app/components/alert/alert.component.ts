import {Component, OnDestroy, OnInit} from '@angular/core';
import {AlertService} from "../../classes/service/alert/alert.service";
import {Subscription} from "rxjs";
import {AlertMessage} from "../../classes/service/alert/alert-message";
import {AlertMessageType} from "../../classes/service/alert/alert-message-type.enum";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'wn-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
  animations: [
    trigger('openClose', [
      state('open', style({ height: '100px' })),
      state('close', style({ height: '0px' })),
      transition('false <=> true', animate(500))
    ])
  ]
})
export class AlertComponent implements OnInit, OnDestroy {

  private subsription: Subscription;
  public alertMessage: AlertMessage;

  constructor(private alertService: AlertService) { }

  ngOnInit() {
    this.subsription = this.alertService.observableMessage.subscribe((alertMessage: AlertMessage) => {
      this.alertMessage = alertMessage;
    });
  }

  ngOnDestroy() {
    this.subsription.unsubscribe();
  }

  isInfoAlert(): boolean {
    return this.alertMessage.getType() == AlertMessageType.INFO;
  }

  isWarningAlert() {
    return this.alertMessage.getType() == AlertMessageType.WARNING;
  }

  isErrorAlert() {
    return this.alertMessage.getType() == AlertMessageType.ERROR;
  }
}
