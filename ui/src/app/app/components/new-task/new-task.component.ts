import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {Task} from "../../classes/task";

@Component({
  selector: 'wn-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  public newTask: any = {text: '', priority: ''};

  @Output() onAddNewTask = new EventEmitter<Task>();

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === "Enter" && this.newTask.text.length > 0 && this.newTask.priority.length > 0) {
      this.addCard(this.newTask.text, this.newTask.priority);
    }
  }

  private addCard(newTaskText: string, newTaskPriority: string) {
    this.onAddNewTask.emit(new Task(newTaskText, newTaskPriority));
    this.newTask.text = '';
    this.newTask.priority = '';
  }

  constructor() {
  }

  ngOnInit() {
  }
}
