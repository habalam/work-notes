import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'wn-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  @Input() tasks: Array<any>;

  constructor() { }

  ngOnInit() {
  }

}
