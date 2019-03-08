import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Task} from "./task";
import {Observable, Subject} from "rxjs";

@Injectable()
export class TaskService {

  //TODO oddelit service na inicializaciu Enumov (Priorities, States) samostatne
  private tasks = new Subject<Array<Task>>();
  private priorities = new Subject<Array<string>>();
  private states = new Subject<Array<string>>();

  private storedPriorities = new Array<string>();
  private storedStates = new Array<string>();

  observableTasks = this.tasks.asObservable();
  observablePriorities = this.priorities.asObservable();
  observableStates = this.states.asObservable();

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

  //TODO stále nemám vyladené - bijú sa mi koncepty Observable a single inicializácia pri spustení/refresh -
  // problém je v tom, že ak by som sa spoľahol iba na Observable implementáciu, tak push operácia (.next)
  // sa môže spustiť ešte predtým ako budú všetci subscribnutý (tj. pre daný Observer bude pole prázdne)
  // ale ak zas použijem statické pole, to tiež nemusí byť v okamihu keď ho budem potrebovať naplnené (a
  // už nemám šancu túto info pushnúť na prešírenie)... problém vyplýva z toho, že v Angulare sa všetky
  // componenty inicializujú súčasne a zároveň call-y na backend sú realizované asynchrónne
  getTaskPriorities() {
    return this.http.get(`/api/task/priorities`).subscribe((priorities: Array<string>) => {
      this.storedPriorities = priorities;
      this.priorities.next(this.storedPriorities);
      this.prioritiesInitialized = true;
    });
  }

  getTaskStates() {
    return this.http.get(`/api/task/states`).subscribe((states: Array<string>) => {
      this.storedStates = states;
      this.states.next(this.storedStates);
      this.statesInitialized = true;
    });
  }

  resendEnums() {
    this.priorities.next(this.storedPriorities);
    this.states.next(this.storedStates);
  }

  getTasks(): Observable<Array<Task>> {
    return this.http.get<Array<Task>>(`/api/task/all`);
  }

  refreshTasks() {
    this.getTasks().subscribe((tasks: Array<Task>) => {
      let queriedTasks = new Array<Task>();
      tasks.forEach((task: Task) => {
        queriedTasks.push(Object.assign(new Task(), task));
      });
      this.tasks.next(queriedTasks);
    });                                                   
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
        this.refreshTasks();
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
        this.refreshTasks();
      });
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
