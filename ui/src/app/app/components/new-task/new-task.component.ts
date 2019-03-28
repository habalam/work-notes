import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {Task} from "../../classes/task";
import {NgForm} from "@angular/forms";
import {TaskService} from "../../classes/task-service";

@Component({
  selector: 'wn-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  public newTask: Task = new Task();

  @ViewChild('form') public form: NgForm;

  public states: Array<string>;
  public priorities: Array<string>;

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.isFormValid()) {
      this.checkAndAddTask();
    }
  }

  constructor(
    private taskService: TaskService
  ) {
    this.taskService.observablePriorities.subscribe((priorities: Array<string>) => {
      this.priorities = priorities;
      this.newTask.priority = priorities[0];
    });
    this.taskService.observableStates.subscribe((states: Array<string>) => {
      this.states = states;
      this.newTask.state = states[0];
    })
  }

  public checkAndAddTask() {
    if (this.isFormValid()) {
      if (this.newTask.created == null) {
        this.newTask.created = new Date();
      }
      this.taskService.addTask(this.newTask);
      this.newTask.text = '';
      this.newTask.priority = this.priorities[0];
      this.newTask.state = this.states[0];
      this.newTask.created = null;
      this.newTask.closed = null;
    }
  }

  private isFormValid() {
    return this.form.valid;
  }

  ngOnInit() {
    if((this.states == null || this.priorities == null)) {
      if (this.taskService.enumsInitialized()) {
        this.priorities = this.taskService.storedPriorities;
        this.states = this.taskService.storedStates;
      }
    }
  }
}
