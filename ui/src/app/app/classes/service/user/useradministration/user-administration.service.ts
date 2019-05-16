import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserData} from "../user-data";

@Injectable({
  providedIn: 'root'
})
export class UserAdministrationService {

  constructor(private http: HttpClient) { }

  public registerUser(userRegistrationData: UserData) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
      })
    };
    return this.http.post(`/api/user/registration`, JSON.stringify(userRegistrationData), httpOptions);
  }
}
