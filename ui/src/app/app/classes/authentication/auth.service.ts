import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {UserAuthData} from "./user-auth-data";
import {AlertService} from "../service/alert/alert.service";

@Injectable()
export class AuthService {

  private currentUserSubject: BehaviorSubject<UserAuthData>;
  public observableUser: Observable<UserAuthData>;

  constructor(private httpClient: HttpClient, private alertService: AlertService) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
    this.observableUser = this.currentUserSubject.asObservable();
  }

  public getCurrentUser() {
    return this.currentUserSubject.value;
  }

  public login(loginData: any) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.httpClient.post('/api/auth/login', JSON.stringify(loginData), httpOptions).subscribe((user: UserAuthData) => {
      if (user.token != null && user.userName != null) {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
      }
    }, () => {
      this.alertService.addErrorMessage("No user exists for given username and password.")
    });
  }

  public logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
