import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Task} from "../../classes/task";
import {NgForm} from "@angular/forms";
import {TaskService} from "../../classes/task-service";

@Component({
  selector: 'wn-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @ViewChild('form') form: NgForm;

  @Input() task: Task;

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

  ngOnInit() {
    this.taskService.getTaskPriorities();
    this.taskService.getTaskStates();
  }

  deleteTask() {
    this.taskService.deleteTask(this.task.id);
  }
}
