import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {BodyComponent} from "./app/components/body/body.component";
import {LoginComponent} from "./app/components/login/login.component";
import {AuthGuard} from "./app/classes/auth-guard";

//TODO pridať default route + wildcard route pre všetky nezadefinované
const routes: Routes = [
  {path: 'notes', component: BodyComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
