export class Task {

  text: string;
  priority: string;
  state: string;

  constructor(text: string, priority: string, state: string) {
    this.text = text;
    this.priority = priority;
    this.state = state;
  }
}
