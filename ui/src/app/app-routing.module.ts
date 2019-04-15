import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BodyComponent} from "./app/components/body/body.component";
import {LoginComponent} from "./app/components/login/login.component";
import {AuthGuard} from "./app/classes/authentication/auth-guard";
import {RegisterComponent} from "./app/components/register/register.component";
import {UserDataUpdateComponent} from "./app/components/user-data-update/user-data-update.component";

//TODO pridať default route + wildcard route pre všetky nezadefinované
const routes: Routes = [
  {path: 'notes', component: BodyComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user-data-update', component: UserDataUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
