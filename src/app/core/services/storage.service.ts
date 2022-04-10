import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';
@Injectable({
  providedIn: 'root'
})
export class StorageService {

  auhtRol: Array<string> = [];

  constructor() { }

  /**
   * permite setear el valor del token
   * @param token
   */
   public settoken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  /**
   * devuelve el token autenticado
   * @returns
   */
  public getToken(): string {
    return window.sessionStorage.getItem(TOKEN_KEY) || '';
  }

  /**
   * permite setear el valor del username
   * @param userName
   */
  public setUserName(userName: string): void {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, userName)
  }

  /**
   *
   * @returns devuelve el username autenticado
   */
  public getUserName(): string {
    return window.sessionStorage.getItem(USERNAME_KEY) || '';
  }

  /**
   * permite setear el valor del authorities
   * @param authorities
   */
  public setAuthorities(authorities: string[]): void {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  /**
   * devuele la lista de authorities, autenticado
   * @returns
   */
  public getAuthorities(): string[] {
    this.auhtRol = [];
    if (window.sessionStorage.getItem(AUTHORITIES_KEY)) {
      JSON.parse(window.sessionStorage.getItem(AUTHORITIES_KEY) || '').foreach((auth: { auht: string; }) => {
        this.auhtRol.push(auth.auht);
      });
    }
    return this.auhtRol;
  }

  /**
   * termina la session del usuario, logoneado
   */
  public logout(): void {
    window.sessionStorage.clear();
  }

}
