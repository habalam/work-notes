import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  private alertMessage = new Subject<string>();
  public observableMessage = this.alertMessage.asObservable();

  constructor() { }

  public addInfoMessage(messageText: string) {
    this.alertMessage.next(messageText);
  }
}
