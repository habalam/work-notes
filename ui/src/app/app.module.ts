import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from "@angular/forms";
import { HttpClientModule} from "@angular/common/http";

import { AppComponent } from './app.component';
import { HeaderComponent } from './app/components/header/header.component';
import { FooterComponent } from './app/components/footer/footer.component';
import { BodyComponent } from './app/components/body/body.component';
import { NewTaskComponent } from './app/components/new-task/new-task.component';
import { TasksListComponent } from './app/components/tasks-list/tasks-list.component';
import { TaskComponent } from './app/components/task/task.component';
import { TaskViewComponent } from './app/components/task-view/task-view.component';
import {TaskService} from "./app/classes/task-service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {OwlDateTimeModule, OwlNativeDateTimeModule} from "ng-pick-datetime";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BodyComponent,
    NewTaskComponent,
    TasksListComponent,
    TaskComponent,
    TaskViewComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
