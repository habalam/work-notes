import {Component, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
import {Task} from "../../classes/task";
import {NgForm} from "@angular/forms";
import {TaskService} from "../../classes/task-service";

@Component({
  selector: 'wn-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  public newTask: Task = new Task("", "", "");

  @Output() onAddNewTask = new EventEmitter<Task>();

  @ViewChild('form') public form: NgForm;

  public states: Array<string> = ["Opened", "Pending", "Closed"];
  public priorities: Array<string>;

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.isFormValid()) {
      this.checkAndAddCard();
    }
  }

  private checkAndAddCard() {
    if (this.isFormValid()) {
      this.onAddNewTask.emit(new Task(this.newTask.text, this.newTask.priority, this.newTask.state));
      this.newTask.text = '';
      this.newTask.priority = this.priorities[0];
      this.newTask.state = this.states[0];
    }
  }

  private isFormValid() {
    return this.form.valid;
  }

  constructor(
    private taskService: TaskService
  ) {}

  ngOnInit() {
    this.taskService.getPriorities().subscribe((priorities: Array<string>) => {
      this.priorities = priorities;
      this.newTask.priority = this.priorities[0];
    });
    this.taskService.getStates().subscribe((states: Array<string>) => {
      this.states = states;
      this.newTask.state = states[0];
    });
  }
}
