import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {AuthguardService} from "../AuthGuardService/authguard.service";

@Injectable({
  providedIn: 'root'
})
export class AdminOrTechnicienAuthGuardService implements CanActivate {

  constructor(private authguardService: AuthguardService, private router: Router) { }

  canActivate(): boolean {
    if (this.authguardService.isAdmin() || this.authguardService.isTechnicien()) {
      return true;
    } else {
      this.router.navigate(['/home']);
      return false;
    }
  }
}
