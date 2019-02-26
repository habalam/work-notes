import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Task} from "./task";

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getTaskPriorities() {
    return this.http.get(`/api/task/priorities`);
  }

  getTaskStates() {
    return this.http.get(`/api/task/states`);
  }

  getTasks() {
    return this.http.get(`/api/task/all`);
  }

  addTask(task: Task) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post(`/api/task/add`, JSON.stringify(task), httpOptions);
  }
}
