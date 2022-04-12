import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jwt } from '../../models/jwt';
import { Usuario } from '../../models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  /**
   * metodo para iniciar session, dentro del aplicativo
   * @param user
   * @returns
   */
  public loginSecurity(user: Usuario): Observable<Jwt> {
    return this.httpClient.post<Jwt>(`${environment.appKruger}/login-controller/login-security`, user);
  }
}
