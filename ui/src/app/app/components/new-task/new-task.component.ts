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

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.form.valid) {
      this.addCard(this.newTask.text, this.newTask.priority, this.newTask.status);
    }
  }

  private addCard(newTaskText: string, newTaskPriority: string, newTaskStatus: string) {
    this.onAddNewTask.emit(new Task(newTaskText, newTaskPriority, newTaskStatus));
    this.newTask.text = '';
    this.newTask.priority = '';
    this.newTask.status = '';
  }

  constructor() {
  }

  ngOnInit() {
  }
}
