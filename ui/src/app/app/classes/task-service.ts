import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {}

  getPriorities() {
    return this.http.get(`/api/task/priorities`);
  }

  getStates() {
    return this.http.get(`/api/task/states`);
  }
}
