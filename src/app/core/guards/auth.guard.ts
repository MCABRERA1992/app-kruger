import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../services/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
    private storageService: StorageService) { }

  canActivate() {
    if (this.storageService.getUserName() != null) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

}
