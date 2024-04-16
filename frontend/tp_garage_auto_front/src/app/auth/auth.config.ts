import { PassedInitialConfig } from 'angular-auth-oidc-client';

export const authConfig: PassedInitialConfig = {
  config: {
    authority: 'http://localhost:8085/realms/tp_garage_automobile',
    redirectUrl: window.location.origin,
    postLogoutRedirectUri: window.location.origin,
    clientId: 'tp-garage-automobile-front',
    scope: 'openid', // 'openid profile offline_access ' + your scopes
    responseType: 'id_token token',
    silentRenew: true,
    useRefreshToken: true,
    renewTimeBeforeTokenExpiresInSeconds: 30,
  },
};
