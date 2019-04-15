import {ParsableJsonObject} from "../../parsable-json-object";

export class TaskTheme implements ParsableJsonObject {

  id: number;
  name: string;
  description: string;
  state: string;
  created: Date;
  closed: Date;

   parseFromJson(taskThemeJson: TaskTheme | any) {
    const taskTheme = new TaskTheme();
    taskTheme.id = taskThemeJson.id;
    taskTheme.name = taskThemeJson.name;
    taskTheme.description = taskThemeJson.description;
    taskTheme.state = taskThemeJson.state;
    taskTheme.created = taskThemeJson.created;
    taskTheme.closed = taskThemeJson.closed;
    return taskTheme;
  }
}
