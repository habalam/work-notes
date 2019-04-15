import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../classes/service/task/task";

@Component({
  selector: 'wn-tasks-by-day',
  templateUrl: './tasks-by-day.component.html',
  styleUrls: ['./tasks-by-day.component.css']
})
export class TasksByDayComponent implements OnInit {

  @Input()
  public filteredTasks: Array<Task>;
  //TODO doimplementovať - chýba celé HTML pre zobrazovanie taskoch po dňoch, týždňoch, ...
  //TODO v rámci @Input-u robiť ordering/sorting (vyrobiť si na to nejakú service, ktorú by som mohol používať všeobecne
  // na tieto účely pre tasky na všetkých views)

  @Input() set tasks(tasks: Array<Task>) {
    this.filteredTasks = tasks;
  }
  

  constructor() {
  }

  ngOnInit() {
  }
}
