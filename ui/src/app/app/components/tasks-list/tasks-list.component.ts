import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../classes/task';

@Component({
  selector: 'wn-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  //TODO buď v tomto komponente, alebo komponente vyššie by sa mali tasky spracovať, tj. pri ich zmene ich roztriediť podľa
  // spoločnej TaskTheme resp. rozdeliť na stromy podľa vzťahov parent-child (toto by sa dalo vracať zo serveru, či radšej nie?)
  // následne už takéto prerozdelené by sa to posielalo hlbšie podľa potreby (či by sme chceli plain zoznam, rozdelenie podľa dní,
  // podľa tém, ...) - použitie set na vykonávanie onChange úprav
  @Input() tasks: Array<Task>;

  constructor() { }

  ngOnInit() {
  }

}
