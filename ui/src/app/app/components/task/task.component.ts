import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Task} from "../../classes/service/task/task";
import {NgForm} from "@angular/forms";
import {TaskService} from "../../classes/service/task/task-service";

@Component({
  selector: 'wn-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @ViewChild('form') form: NgForm;

  @Input() task: Task;

  public currentTask: Task;

  public priorities: Array<string>;
  public states: Array<string>;

  constructor(private taskService: TaskService) {
    this.taskService.observablePriorities.subscribe((priorities: Array<string>) => {
      this.priorities = priorities;
    });
    this.taskService.observableStates.subscribe((states: Array<string>) => {
      this.states = states;
    });
  }

  deleteTask() {
    this.taskService.deleteTask(this.task.id);
  }

  updateIfNeeded() {
    if (this.task.text != this.currentTask.text || this.task.priority != this.currentTask.priority ||
      this.task.state != this.currentTask.state)
    {
      //TODO na GUI by sa mali info zmeniť až po tom, ako update úspešne dobehne update na backend-e tj. keď sú dáta uložené v DB
      this.task.copyValues(this.currentTask);
      this.taskService.updateTask(this.task);
    }
  }

  ngOnInit() {
    if ((this.states == null || this.priorities == null)) {
      if (this.taskService.enumsInitialized()) {
        this.priorities = this.taskService.storedPriorities;
        this.states = this.taskService.storedStates;
      }
    }
    this.currentTask = this.task.clone();
  }
}
