import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Subject} from "rxjs";

@Injectable()
export class TaskService {

  private tasks = new Subject<Array<Task>>();

  observableTasks = this.tasks.asObservable();

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
    this.http.post(`/api/task/add`, JSON.stringify(task), httpOptions)
      .subscribe(response => console.log(response), error => console.log(error), () => {
        this.getTasks().subscribe((tasks: Array<Task>) => {
          this.tasks.next(tasks);
        })
      });
  }

  deleteTask(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post(`/api/task/delete`, JSON.stringify(id), httpOptions)
      .subscribe(response => console.log(response), error => console.log(error), () => {
      this.getTasks().subscribe((tasks: Array<Task>) => {
        this.tasks.next(tasks);
      })
    });
  }
}
