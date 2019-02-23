import {Component, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
import {Task} from "../../classes/task";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'wn-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  public newTask: Task = new Task("", "", "");

  @Output() onAddNewTask = new EventEmitter<Task>();

  @ViewChild('form') public form: NgForm;

  public statuses: Array<string> = ["Opened", "Pending", "Closed"];
  public priorities: Array<string> = ["None", "Low", "Medium", "High", "Top"];

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.isFormValid()) {
      this.checkAndAddCard();
    }
  }

  private checkAndAddCard() {
    if(this.isFormValid()) {
      this.onAddNewTask.emit(new Task(this.newTask.text, this.newTask.priority, this.newTask.status));
      this.newTask.text = '';
      this.newTask.priority = this.priorities[0];
      this.newTask.status = this.statuses[0];
    }
  }

  private isFormValid() {
    return this.form.valid;
  }

  constructor() {
  }

  ngOnInit() {
    this.newTask.priority = this.priorities[0];
    this.newTask.status = this.statuses[0];
  }
}
