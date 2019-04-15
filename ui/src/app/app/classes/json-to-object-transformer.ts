import {ParsableJsonObject} from "./parsable-json-object";

export class JsonToObjectTransformer {

  public static transformObjectArrayJsonToObjects<T extends ParsableJsonObject>(arrayType: new() => T, jsonObjects: Array<any>): Array<T> {
    let queriedTasks = new Array<T>();
    jsonObjects.forEach((jsonObject: any) => {
      queriedTasks.push(this.transformJsonToObject(arrayType, jsonObject));
    });
    return queriedTasks;
  }

  public static transformJsonToObject<T extends ParsableJsonObject>(obj: new() => T, jsonObject: any): T {
    const object = new obj();
    object.parseFromJson(jsonObject);
    return object;
  }
}
