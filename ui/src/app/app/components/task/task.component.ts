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

  constructor(private taskServide: TaskService) { }

  ngOnInit() {
  }

  deleteTask() {
    this.taskServide.deleteTask(this.task.id);
  }
}
