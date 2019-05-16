import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {AuthService} from "./authentication/auth.service";
import {catchError} from "rxjs/operators";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  private static LOGIN_URL: string = "/login";

  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err => {
      if (err.status === 401) {
        this.authService.logout();
        if (location.pathname !== ErrorInterceptor.LOGIN_URL) {
          location.assign(ErrorInterceptor.LOGIN_URL);
        }
        //TODO prečo by som mal v tomto prípade reloadovať
        //location.reload();
      }

      const error = err.error.message || err.statusText;
      return throwError(error);
    }))
  }
}
