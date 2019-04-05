import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Observable, Subscriber} from "rxjs";
import {JsonToObjectTransformer} from "./json-to-object-transformer";

@Injectable()
export class TaskService {

  //TODO formulárové dáta ukladať do storage(local, session) - toto by vyriešilo
  // problém pri refreshy stránky - napr. aby sa otvoril tab, ktorý bol otvorený predtým

  //TODO oddelit service na inicializaciu Enumov (Priorities, States) samostatne
  private tasksObserver = new Subscriber<Array<Task>>();
  private prioritiesObserver = new Subscriber<Array<string>>();
  private statesObserver = new Subscriber<Array<string>>();

  public storedPriorities = new Array<string>();
  public storedStates = new Array<string>();

  observableTasks = new Observable<Array<Task>>(o => this.tasksObserver = o);
  observablePriorities = new Observable<Array<string>>(o => this.prioritiesObserver = o);
  observableStates = new Observable<Array<string>>(o => this.statesObserver = o);

  private prioritiesInitialized = false;
  private statesInitialized = false;

  constructor(private http: HttpClient) {
    this.initializeEnums();
  }

  private initializeEnums() {
    this.prioritiesInitialized = false;
    this.statesInitialized = false;
    this.getTaskPriorities();
    this.getTaskStates();
  }

  enumsInitialized(): boolean {
    return this.prioritiesInitialized && this.statesInitialized;
  }

  getTaskPriorities() {
    return this.http.get(`/api/task/priorities`).subscribe((priorities: Array<string>) => {
      this.storedPriorities = priorities;
      this.prioritiesObserver.next(this.storedPriorities);
      this.prioritiesInitialized = true;
    });
  }

  getTaskStates() {
    return this.http.get(`/api/task/states`).subscribe((states: Array<string>) => {
      this.storedStates = states;
      this.statesObserver.next(this.storedStates);
      this.statesInitialized = true;
    });
  }

  getTasks(): Observable<Array<Task>> {
    return this.http.get<Array<Task>>(`/api/task/all`);
  }

  refreshTasks() {
    this.getTasks().subscribe((tasks: Array<Task>) => {
      let queriedTasks = JsonToObjectTransformer.transformObjectArrayJsonToObjects(Task, tasks);
      this.tasksObserver.next(queriedTasks);
    });                                                   
  }

  addTask(task: Task) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post(`/api/task/add`, JSON.stringify(task), httpOptions)
      .subscribe(() => this.refreshTasks());
  }

  deleteTask(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post(`/api/task/delete`, JSON.stringify(id), httpOptions)
      .subscribe(() => this.refreshTasks());
  }

  updateTask(task: Task) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post(`/api/task/update`, JSON.stringify(task), httpOptions)
      .subscribe(response => console.log(response), error => console.log(error));
  }
}
