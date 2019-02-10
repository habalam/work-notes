import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../classes/task";

@Component({
  selector: 'wn-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input() task: Task;

  constructor() { }

  ngOnInit() {
  }

}
