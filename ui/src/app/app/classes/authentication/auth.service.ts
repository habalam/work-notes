import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {UserData} from "./user-data";

@Injectable()
export class AuthService {

  private currentUserSubject: BehaviorSubject<UserData>;
  public observableUser: Observable<UserData>;

  constructor(private httpClient: HttpClient) {
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
    this.httpClient.post('/api/auth/login', JSON.stringify(loginData), httpOptions).subscribe((user: UserData) => {
      if (user.token != null && user.userName != null) {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
      }
    });
  }

  public logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
