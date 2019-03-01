import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Subject} from "rxjs";

@Injectable()
export class TaskService {

  private tasks = new Subject<Array<Task>>();
  private priorities = new Subject<Array<String>>();
  private states = new Subject<Array<String>>();

  observableTasks = this.tasks.asObservable();
  observablePriorities = this.priorities.asObservable();
  observableStates = this.states.asObservable();

  constructor(private http: HttpClient) {
  }

  getTaskPriorities() {
    return this.http.get(`/api/task/priorities`).subscribe((priorities: Array<string>) => {
      this.priorities.next(priorities);
    });
  }

  getTaskStates() {
    return this.http.get(`/api/task/states`).subscribe((states: Array<string>) => {
      this.states.next(states);
    });
  }

  getTasks() {
    return this.http.get(`/api/task/all`);
  }

  //TODO do konzoly loguje null, fixnúť
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

  //TODO do konzoly loguje null, fixnúť
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
