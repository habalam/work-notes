import { Component, OnInit } from '@angular/core';
import {Task} from "../../classes/task";

@Component({
  selector: 'wn-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  public tasks: Array<Task> = [];

  constructor() { }

  ngOnInit() {
  }

  addTask(task: Task) {
    this.tasks.push(task);
  }
}
