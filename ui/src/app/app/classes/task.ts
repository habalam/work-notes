import {TaskTheme} from "./task-theme";
import {ParsableJsonObject} from "./parsable-json-object";

export class Task implements ParsableJsonObject {

  id: number;
  text: string;
  priority: string;
  state: string;
  created: Date;
  closed: Date;
  taskTheme: TaskTheme;

  clone(): Task {
    const newTask = new Task();
    newTask.parseFromJson(this);
    return newTask;
  }
  parseFromJson(jsonObject: any) {
    this.id = jsonObject.id;
    this.text = jsonObject.text;
    this.priority = jsonObject.priority;
    this.state = jsonObject.state;
    this.created = jsonObject.created;
    this.closed = jsonObject.closed;
    if(jsonObject.taskTheme != null) {
      if (this.taskTheme != null) {
        this.taskTheme.parseFromJson(jsonObject.taskTheme);
      }
      else {
        this.taskTheme = new TaskTheme().parseFromJson(jsonObject.taskTheme);
      }
    }
  }

  copyValues(copiedTask: Task) {
    this.text = copiedTask.text;
    this.priority = copiedTask.priority;
    this.state = copiedTask.state;
    this.created = copiedTask.created;
    this.closed = copiedTask.closed;
  }
}
