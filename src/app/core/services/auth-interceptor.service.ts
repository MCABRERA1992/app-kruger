import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private tokenService: StorageService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let intReq = request;
    const token = this.tokenService.obtenerUser();
    if (token && token.access_token) {
      intReq = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token.access_token) });
    }
    return next.handle(intReq);
  }
}
