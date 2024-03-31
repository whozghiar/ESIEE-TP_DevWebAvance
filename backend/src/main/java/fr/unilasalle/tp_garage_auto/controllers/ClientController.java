package fr.unilasalle.tp_garage_auto.controllers;

import fr.unilasalle.tp_garage_auto.DTO.ClientDTO;
import fr.unilasalle.tp_garage_auto.beans.Client;
import fr.unilasalle.tp_garage_auto.exceptions.DBException;
import fr.unilasalle.tp_garage_auto.exceptions.DTOException;
import fr.unilasalle.tp_garage_auto.exceptions.NotFoundException;
import fr.unilasalle.tp_garage_auto.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.unilasalle.tp_garage_auto.services.ClientService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    /**
     * Méthode GET pour récupérer tous les clients
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Client>> getClient() {
        log.info("Récupération de tous les clients...");
        try{
            Set<Client> clients = this.clientService.getAllClients();
            log.info("Clients récupérés avec succès : \n\t" + clients);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Erreur lors de la récupération des clients.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Méthode GET pour récupérer un client par id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        try {
            log.info("Récupération du client avec l'id " + id);
            Client client = this.clientService.getClientById(id);
            log.info("Client récupéré avec succès : \n\t" + client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Méthode GET pour récupérer les clients par nom
     * @param nom
     * @return
     */
    @GetMapping()
    public ResponseEntity<Set<Client>> getClientByNom(@RequestParam String nom) {
        try {
            log.info("Récupération du client avec le nom " + nom);
            Set<Client> clients = this.clientService.getClientsByName(nom);
            log.info("Client récupéré avec succès : \n\t" + clients);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec le nom " + nom + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les clients par prénom
     * @param prenom
     * @return
     */
    @GetMapping
    public ResponseEntity<Set<Client>> getClientByPrenom(@RequestParam String prenom) {
        try {
            log.info("Récupération du client avec le prenom " + prenom);
            Set<Client> clients = this.clientService.getClientsBySurname(prenom);
            log.info("Client récupéré avec succès : \n\t" + clients);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec le nom " + prenom + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les clients par email
     * @param email
     * @return
     */
    @GetMapping
    public ResponseEntity<Client> getClientByEmail(@RequestParam String email) {
        try {
            log.info("Récupération du client avec l'email " + email);
            Client client = this.clientService.getClientsByEmail(email);
            log.info("Client récupéré avec succès : \n\t" + client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec l'email " + email + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode GET pour récupérer les clients par téléphone
     * @param telephone
     * @return
     */
    @GetMapping
    public ResponseEntity<Client> getClientByTelephone(@RequestParam String telephone) {
        try {
            log.info("Récupération du client avec le téléphone " + telephone);
            Client client = this.clientService.getClientsByPhone(telephone);
            log.info("Client récupéré avec succès : \n\t" + client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec le téléphone " + telephone + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Méthode POST pour créer un client
     * @param client
     * @return
     */
    @PostMapping
    public ResponseEntity<Client> postClient(@RequestBody Client client) {
        try {
            log.info("Création d'un client ...");
            Client savedObjet = this.clientService.createClient(client);
            log.info("Client créé avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.CREATED);
        } catch (DBException e) {
            log.error("Erreur lors de l'enregistrement du client", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec l'id " + client.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            log.error("Erreur avec le DTO : ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            log.error("Erreur de service : ", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Méthode PUT pour mettre à jour un client
     * @param id
     * @param client
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client) {
        try {
            log.info("Mis à jour du client ...");
            Client savedObjet = this.clientService.updateClient(id,client);
            log.info("Client mis à jour avec succès : \n\t" + savedObjet);
            return new ResponseEntity<>(savedObjet, HttpStatus.ACCEPTED);
        } catch (DBException e) {
            log.error("Erreur lors de la mise à jour du client.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec l'id " + client.getId() + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DTOException e) {
            log.error("Erreur avec le DTO : ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            log.error("Erreur de service : ", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Méthode DELETE pour supprimer un client
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            log.info("Suppression du client avec l'id " + id);
            this.clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.error("Impossible de trouver le client avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DBException e) {
            log.error("Erreur lors de la suppression du client avec l'id " + id + ".", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
