import {ParsableJsonObject} from "./parsable-json-object";

export class JsonToObjectTransformer {

  public static transformObjectArrayJsonToObjects<T extends ParsableJsonObject>(arrayType: new() => T, tasks: Array<any>): Array<T> {
    let queriedTasks = new Array<T>();
    tasks.forEach((task: any) => {
      queriedTasks.push(this.transformJsonToObject(arrayType, task));
    });
    return queriedTasks;
  }

  public static transformJsonToObject<T extends ParsableJsonObject>(obj: new() => T, jsonTask: any): T {
    const object = new obj();
    object.parseFromJson(jsonTask);
    return object;
  }
}
