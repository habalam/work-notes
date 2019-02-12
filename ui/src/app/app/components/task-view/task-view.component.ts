import { Component, OnInit } from '@angular/core';
import {Task} from "../../classes/task";

@Component({
  selector: 'wn-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  public tasks: Array<Task> = [];

  constructor() { }

  ngOnInit() {
  }

  addTask(task: Task) {
    this.tasks.push(task);
  }
}
