export class Task {

  id: number;
  text: string;
  priority: string;
  state: string;
  created: Date;
  closed: Date;

  cloneTask(): Task {
    const newTask = new Task();
    newTask.id = this.id;
    newTask.text = this.text;
    newTask.priority = this.priority;
    newTask.state = this.state;
    newTask.created = this.created;
    newTask.closed = this.closed;
    return newTask;
  }

  copyTaskValues(copiedTask: Task) {
    this.text = copiedTask.text;
    this.priority = copiedTask.priority;
    this.state = copiedTask.state;
    this.created = copiedTask.created;
    this.closed = copiedTask.closed;
  }
}
