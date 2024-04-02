package fr.unilasalle.tp_garage_auto.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Requêtes autorisées
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/", "/images/**").permitAll()

                        // Autoriser les opérations de CRUD sur l'entité Vehicule
                        .requestMatchers(HttpMethod.GET, "/vehicule/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/vehicule/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.PUT, "/vehicule/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE, "/vehicule/**").hasAnyRole("ADMIN")

                        // Autoriser les opérations de CRUD sur l'entité Client
                        .requestMatchers(HttpMethod.GET, "/client/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/client/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.PUT, "/client/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE, "/client/**").hasAnyRole("ADMIN")

                        // Autoriser les opérations de CRUD sur l'entité RendezVous
                        .requestMatchers(HttpMethod.GET, "/rendez-vous/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/rendez-vous/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.PUT, "/rendez-vous/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE, "/rendez-vous/**").hasAnyRole("ADMIN")

                        // Autoriser les opérations de CRUD sur l'entité Technicien
                        .requestMatchers(HttpMethod.GET, "/technicien/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/technicien/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.PUT, "/technicien/**").hasAnyRole("ADMIN","EMPLOYE")
                        .requestMatchers(HttpMethod.DELETE, "/technicien/**").hasAnyRole("ADMIN")

                        // Ne pas autoriser les autres requêtes
                        .anyRequest().authenticated()
                )
                // Autoriser les requêtes POST/PUT/DELETE
                .csrf(AbstractHttpConfigurer::disable)


                // OAuth 2.0 Authentification
                .oauth2Login(withDefaults())

                // Déconnexion
                .logout(logout -> logout
                        .addLogoutHandler(logoutHandler()));

        return http.build();
    }

    /**
     * Gestionnaire de déconnexion
     * @return
     */
    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
