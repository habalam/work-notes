import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../classes/task';

@Component({
  selector: 'wn-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  @Input() tasks: Array<Task>;

  constructor() { }

  ngOnInit() {
  }

}
