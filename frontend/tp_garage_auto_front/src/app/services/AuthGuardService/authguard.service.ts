import {inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {OidcSecurityService} from "angular-auth-oidc-client";
import {Observable, of} from "rxjs";
import {catchError, map, switchMap, take} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthguardService{
  private readonly oidcSecurityService = inject(OidcSecurityService);
  private readonly router = inject(Router);

  private admin: boolean = false;
  private client: boolean = false;
  private technicien: boolean = false;

  private email: string = '';
  private given_name: string = '';
  private family_name: string = '';
  private roles: string[] = [];

  constructor() {
    this.initializeAttributes();
  }

  private initializeAttributes() {
    console.log("Authguard Service initialization...");
    this.oidcSecurityService.userData$.subscribe(userData => {
      this.email = this.getUserEmail(userData);
      this.given_name = this.getUserName(userData);
      this.family_name = this.getUserSurname(userData);
      this.roles = userData.userData.realm_access.roles;

      console.log("User roles: " + this.roles);

      this.admin = this.checkAdminStatus(userData);
      this.client = this.checkClientStatus(userData);
      this.technicien = this.checkTechnicienStatus(userData);
    });
  }


  private checkAdminStatus(userData: any): boolean {
    return userData.userData.realm_access.roles.includes('admin');
  }

  private checkClientStatus(userData: any): boolean {
    return userData.userData.realm_access.roles.includes('client');
  }

  private checkTechnicienStatus(userData: any): boolean {
    return userData.userData.realm_access.roles.includes('technicien');
  }

/**
 * Récupère l'adresse email de l'utilisateur Keycloak connecté
 * @param userData
 * @private
 */
  getUserEmail(userData: any): string {
      return userData.userData.email;
  }

  /**
   * Récupère le nom de l'utilisateur Keycloak connecté
   * @param userData
   * @private
   */
  getUserName(userData: any): string {
    return userData.userData.given_name;
  }

  /**
   * Récupère le prénom de l'utilisateur Keycloak connecté
   * @param userData
   * @private
   */
  getUserSurname(userData: any): string {
    return userData.userData.family_name;
  }

  /**
   * Récupère le statut de l'utilisateur
   */
  isAdmin(): boolean {
    return this.admin;
  }

  /**
   * Récupère le statut de l'utilisateur
   */
  isClient(): boolean {
    return this.client;
  }

  /**
   * Récupère le statut de l'utilisateur
   */
  isTechnicien(): boolean {
    return this.technicien;
  }

  /**
   * Récupère l'adresse email de l'utilisateur
   */
  getEmail(): string {
    return this.email;
  }

  /**
   * Récupère le prénom de l'utilisateur
   */
  getGivenName(): string {
    return this.given_name;
  }

  /**
   * Récupère le nom de famille de l'utilisateur
   */
  getFamilyName(): string {
    return this.family_name;
  }

  /**
   * Récupère les rôles de l'utilisateur
   */
  getUserRoles(): string[] {
    return this.roles;
  }
}
