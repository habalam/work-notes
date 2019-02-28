import {Component, OnInit} from '@angular/core';
import {Task} from "../../classes/task";
import {TaskService} from "../../classes/task-service";

@Component({
  selector: 'wn-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  public tasks: Array<Task>;
  public tasksCount: number;

  constructor(private taskService: TaskService) {
    this.taskService.observableTasks.subscribe((tasks: Array<Task>) => {
      this.tasks = tasks;
      this.tasksCount = tasks.length;
    });
  }

  ngOnInit() {
    this.taskService.getTasks().subscribe((tasks: Array<Task>) => {
      this.tasks = tasks;
      this.tasksCount = tasks.length;
    })
  }
}
