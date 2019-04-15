import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import {AppComponent} from './app.component';
import {HeaderComponent} from './app/components/header/header.component';
import {FooterComponent} from './app/components/footer/footer.component';
import {BodyComponent} from './app/components/body/body.component';
import {NewTaskComponent} from './app/components/new-task/new-task.component';
import {TasksListComponent} from './app/components/tasks-list/tasks-list.component';
import {TaskComponent} from './app/components/task/task.component';
import {TaskViewComponent} from './app/components/task-view/task-view.component';
import {TaskService} from "./app/classes/service/task/task-service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {OwlDateTimeModule, OwlNativeDateTimeModule} from "ng-pick-datetime";
import {TasksByDayComponent} from './app/components/tasks-by-day/tasks-by-day.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from "./app/components/login/login.component";
import {AuthService} from "./app/classes/authentication/auth.service";
import {JwtInterceptor} from "./app/classes/authentication/jwt-interceptor";
import {RegisterComponent} from './app/components/register/register.component';
import {AlertComponent} from './app/components/alert/alert.component';
import {UserDataUpdateComponent} from './app/components/user-data-update/user-data-update.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BodyComponent,
    NewTaskComponent,
    TasksListComponent,
    TaskComponent,
    TaskViewComponent,
    TasksByDayComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    UserDataUpdateComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    TaskService,
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
