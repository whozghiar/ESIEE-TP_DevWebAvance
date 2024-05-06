import {Component, inject, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import {OidcSecurityService} from "angular-auth-oidc-client";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit{

  title = 'tp_garage_auto_front';
  private readonly oidcSecurityService = inject(OidcSecurityService);


  constructor(
  ) {}

  ngOnInit() {
    this.oidcSecurityService.checkAuth().subscribe(({ isAuthenticated, userData,accessToken}) => {
      console.log('app authenticated', isAuthenticated);
      console.log('app userData', userData);
      console.log("app token", accessToken);

      if (!isAuthenticated) {
        this.login();

      }
    });
  }

  login() {
    this.oidcSecurityService.authorize();
  }

  logout() {
    this.oidcSecurityService.logoff().subscribe((result) => console.log(result));
  }
}

