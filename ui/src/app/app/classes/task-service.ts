import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {}

  getTaskPriorities() {
    return this.http.get(`/api/task/priorities`);
  }

  getTaskStates() {
    return this.http.get(`/api/task/states`);
  }

  getTasks() {
    return this.http.get(`/api/task/all`);
  }
}
