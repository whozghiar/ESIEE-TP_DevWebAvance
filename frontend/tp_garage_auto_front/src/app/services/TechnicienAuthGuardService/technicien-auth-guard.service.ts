import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {AuthguardService} from "../AuthGuardService/authguard.service";

@Injectable({
  providedIn: 'root'
})
export class TechnicienAuthGuardService implements CanActivate {

  constructor(private authguardService: AuthguardService, private router: Router) { }

  canActivate(): boolean {
    if (this.authguardService.isTechnicien()) {
      return true;
    } else {
      this.router.navigate(['/home']);
      return false;
    }
  }
}
