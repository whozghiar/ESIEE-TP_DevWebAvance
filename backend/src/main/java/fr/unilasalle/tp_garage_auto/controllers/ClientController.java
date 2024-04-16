package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import fr.unilasalle.tp_garage_auto.services.ClientService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
@Slf4j
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class ClientController {

    private final ClientService clientService;


    /**
     * Méthode GET pour récupérer tous les clients
     * @param nom : nom du client
     * @param prenom : prenom du client
     * @param email : email du client
     * @param telephone : telephone du client
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer tous les clients",
            description = "Récupérer tous les clients en fonction de certains critères",
            tags = { "client" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<?> getClients(@RequestParam(required = false) String nom,
                                        @RequestParam(required = false) String prenom,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false) String telephone) throws ServiceException {
        if (nom != null) {
            return getClientByNom(nom);
        } else if (prenom != null) {
            return getClientByPrenom(prenom);
        } else if (email != null) {
            return getClientByEmail(email);
        } else if (telephone != null) {
            return getClientByTelephone(telephone);
        } else {
            log.info("Récupération de tous les clients ...");
            Set<Client> clients = this.clientService.getAllClients();
            log.info("Clients récupérés avec succès : \n\t" + clients);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

    /**
     * Méthode GET pour récupérer un client par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien','client')")
    @Operation(summary = "Récupérer un client par son id",
            description = "Récupérer un client par son id",
            tags = { "client" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        log.info("Récupération du client avec l'id " + id);
        Client client = this.clientService.getClientById(id);
        log.info("Client récupéré avec succès : \n\t" + client);
        return new ResponseEntity<>(client, HttpStatus.OK);

    }


    /**
     * Récupérer les clients par nom
     * @param nom
     * @return
     */
    public ResponseEntity<Set<Client>> getClientByNom(String nom) {
        log.info("Récupération du client avec le nom " + nom);
        Set<Client> clients = this.clientService.getClientsByName(nom);
        log.info("Client récupéré avec succès : \n\t" + clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Récupérer les clients par prénom
     * @param prenom
     * @return
     */
    public ResponseEntity<Set<Client>> getClientByPrenom(String prenom) {

        log.info("Récupération du client avec le prenom " + prenom);
        Set<Client> clients = this.clientService.getClientsBySurname(prenom);
        log.info("Client récupéré avec succès : \n\t" + clients);
        return new ResponseEntity<>(clients, HttpStatus.OK);

    }

    /**
     * Récupérer les clients par email
     * @param email
     * @return
     */
    public ResponseEntity<Client> getClientByEmail(String email) {

        log.info("Récupération du client avec l'email " + email);
        Client client = this.clientService.getClientByEmail(email);
        log.info("Client récupéré avec succès : \n\t" + client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Récupérer les clients par téléphone
     * @param telephone
     * @return
     */
    public ResponseEntity<Client> getClientByTelephone(String telephone) {

        log.info("Récupération du client avec le téléphone " + telephone);
        Client client = this.clientService.getClientsByPhone(telephone);
        log.info("Client récupéré avec succès : \n\t" + client);
        return new ResponseEntity<>(client, HttpStatus.OK);

    }


    /**
     * Méthode POST pour créer un client
     * @param client
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Créer un client",
            description = "Créer un client",
            tags = { "client" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Client> postClient(@RequestBody Client client) throws ServiceException {

        log.info("Création d'un client ...");
        Client savedObjet = this.clientService.createClient(client);
        log.info("Client créé avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);

    }

    /**
     * Méthode PUT pour mettre à jour un client
     * @param id
     * @param client
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Mettre à jour un client",
            description = "Mettre à jour un client",
            tags = { "client" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client) throws ServiceException {
        log.info("Mis à jour du client ...");
        Client savedObjet = this.clientService.updateClient(id,client);
        log.info("Client mis à jour avec succès : \n\t" + savedObjet);
        return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
    }

    /**
     * Méthode DELETE pour supprimer un client
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','technicien')")
    @Operation(summary = "Supprimer un client",
            description = "Supprimer un client",
            tags = { "client" },
            security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.info("Suppression du client avec l'id " + id);
        this.clientService.deleteClient(id);
        log.info("Client supprimé avec succès.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
