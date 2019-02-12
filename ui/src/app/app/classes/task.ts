export class Task {

  text: string;
  priority: string;
  status: string;

  constructor(text: string, priority: string, status: string) {
    this.text = text;
    this.priority = priority;
    this.status = status;
  }
}
