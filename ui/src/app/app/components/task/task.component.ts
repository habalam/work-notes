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

  private currentTask: Task;

  private priorities: Array<string>;
  private states: Array<string>;

  constructor(private taskService: TaskService) {
    this.taskService.observablePriorities.subscribe((priorities: Array<string>) => {
      this.priorities = priorities;
    });
    this.taskService.observableStates.subscribe((states: Array<string>) => {
      this.states = states;
    });
  }

  ngOnInit() {
    if ((this.states == null || this.priorities.length == null) && this.taskService.enumsInitialized()) {
      this.taskService.resendEnums();
    }
    this.currentTask = new Task(this.task.text, this.task.priority, this.task.state);
  }

  deleteTask() {
    this.taskService.deleteTask(this.task.id);
  }

  updateIfNeeded() {
    if (this.task.text != this.currentTask.text || this.task.priority != this.currentTask.priority ||
      this.task.state != this.currentTask.state)
    {
      //TODO na GUI by sa mali info zmeniť až po tom, ako update úspešne dobehne update na backend-e
      this.task.text = this.currentTask.text;
      this.task.priority = this.currentTask.priority;
      this.task.state = this.currentTask.state;

      this.taskService.updateTask(this.task);
    }
  }
}
