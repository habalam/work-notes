import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {AlertMessage} from "./alert-message";
import {AlertMessageType} from "./alert-message-type.enum";

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  private alertMessage = new Subject<AlertMessage>();
  public observableMessage = this.alertMessage.asObservable();

  constructor() { }

  public addInfoMessage(messageText: string) {
    this.alertMessage.next(new AlertMessage(messageText, AlertMessageType.INFO));
  }

  public addWarningMessage(messageText: string) {
    this.alertMessage.next(new AlertMessage(messageText, AlertMessageType.WARNING));
  }

  public addErrorMessage(messageText: string) {
    this.alertMessage.next(new AlertMessage(messageText, AlertMessageType.ERROR));
  }
}
