import {AlertMessageType} from "./alert-message-type.enum";

export class AlertMessage {

  private messageText: string;
  private messageType: AlertMessageType;

  constructor(messageText: string, messageType: AlertMessageType) {
    this.messageText = messageText;
    this.messageType = messageType;
  }

  public getText(): string {
    return this.messageText;
  }

  public getType(): AlertMessageType {
    return this.messageType;
  }
}
