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

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClient() {
        log.info("Récupération de tous les clients...");
        List<Client> clients = this.clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

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

    @PutMapping
    public ResponseEntity<Client> putClient(@RequestBody Client client) {
        try {
            log.info("Mis à jour du client ...");
            Client savedObjet = this.clientService.updateClient(client);
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
