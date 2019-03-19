import {Component, OnInit} from '@angular/core';
import {TaskTabView} from "../../enums/task-tabs.enum";
import {TaskService} from "../../classes/task-service";
import {Task} from '../../classes/task';

@Component({
  selector: 'wn-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  public taskTabViews: any = TaskTabView;

  public tabSelected = TaskTabView.SIMPLE_LIST_VIEW;
  public tasks: Array<Task>;
  public tasksCount: number;

  constructor(private taskService: TaskService) {
    this.taskService.observableTasks.subscribe((tasks: Array<Task>) => {
      this.tasks = tasks;
      this.tasksCount = tasks.length;
    });
  }

  ngOnInit() {
    this.taskService.refreshTasks();
  }

  changeSelectedTab(tabSelected: TaskTabView) {
    this.tabSelected = tabSelected;
  }
}
