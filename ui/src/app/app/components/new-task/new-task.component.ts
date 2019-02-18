import {Component, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
import {Task} from "../../classes/task";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'wn-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  public newTask: Task = new Task('', '', '');

  @Output() onAddNewTask = new EventEmitter<Task>();

  @ViewChild('form') public form: NgForm;

  public statuses: Array<string> = ["Opened", "Pending", "Closed"];
  public priorities: Array<string> = ["None", "Low", "Medium", "High", "Top"];

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.form.valid) {
      this.checkAndAddCard(this.newTask.text, this.newTask.priority, this.newTask.status);
    }
  }

  private checkAndAddCard(newTaskText: string, newTaskPriority: string, newTaskStatus: string) {
    if(this.isFormValid()) {
      this.onAddNewTask.emit(new Task(newTaskText, newTaskPriority, newTaskStatus));
      this.newTask.text = '';
      this.newTask.priority = '';
      this.newTask.status = '';
    }
  }

  private isFormValid() {
    return this.form.valid;
  }

  constructor() {
  }

  ngOnInit() {
  }
}
