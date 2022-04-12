import { Injectable } from '@angular/core';
import { Jwt } from '../models/jwt';

const TOKEN_KEY = 'Token';
const USER_KEY = 'Usuario';
const AUTHORITIES = 'authorities';
const TIMEOUT = 'TimeOut';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private static readonly KEY_USUARIO = "kruger.usuario";
  rolUsuario: Array<string> = [];

  constructor() { }

  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public obtenerToken(): string {
    return window.sessionStorage.getItem(TOKEN_KEY) || '';
  }

  public setUsuario(usuario: string): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, usuario);
  }


  public getUsuario(): string {
    return sessionStorage.getItem(USER_KEY) || '';
  }

  public setAuthorities(authorities: string[]): void {
    window.sessionStorage.removeItem(AUTHORITIES);
    window.sessionStorage.setItem(AUTHORITIES, JSON.stringify(authorities));
  }

  public obtenerAuthorities(): string[] {
    this.rolUsuario = [];
    if (sessionStorage.getItem(AUTHORITIES)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES) || '').forEach((element: { authority: string; }) => {
        this.rolUsuario.push(element.authority);
      });
    }
    return this.rolUsuario;
  }


  public setTimeOut(timeOut: string): void {
    window.sessionStorage.removeItem(TIMEOUT);
    window.sessionStorage.setItem(TIMEOUT, timeOut);
  }

  public setUser(jwtToken: Jwt): void {
    window.sessionStorage.removeItem(StorageService.KEY_USUARIO);
    window.sessionStorage.setItem(StorageService.KEY_USUARIO, JSON.stringify(jwtToken));
  }

  public obtenerUser(): Jwt {
    let user = sessionStorage.getItem(StorageService.KEY_USUARIO);
    return JSON.parse(user||'');
  }

  public logout(): void {
    window.sessionStorage.clear();
  }

}
